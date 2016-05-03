# ------------------------------------
# VARIABLES
# ------------------------------------

$dbpassword = 'test1234'

#Host entry used for symfony app
host { 'myapp':
    ip => '192.168.33.100',
    host_aliases => 'myapp.dev',
}

# Install our dependencies

exec {"apt-get update":
  path => "/usr/bin",
}

package {"vim":
  ensure => present,
  require => Exec["apt-get update"],
}

package {"python":
  ensure => present,
  require => Exec["apt-get update"],
}

package {"apache2":
  ensure => present,
  require => Exec["apt-get update"],
}

service { "apache2":
  ensure => "running",
  require => Package["apache2"]
}

package {['postgresql', 'postgresql-contrib']:
  ensure => installed,
  require => Exec["apt-get update"]
}

package { ['openjdk-7-jdk', 'maven']:
  ensure => installed,
  require => Exec["apt-get update"],
}

service { 'postgresql':
  ensure  => running,
  require => Package['postgresql'],
}

package { "python-crypto":
  ensure => present,
  require => [Exec["apt-get update"], Package['python']],
}

package { "git":
  ensure => installed,
  require => [Exec["apt-get update"]],
}

# Install latest Node.js and npm, instead of Ubuntu 14.04's 
exec { "AddNodePPA" :
	command => "/usr/bin/curl -s https://deb.nodesource.com/gpgkey/nodesource.gpg.key | /usr/bin/apt-key add - && echo 'deb https://deb.nodesource.com/node_5.x trusty main' > /etc/apt/sources.list.d/nodesource.list && /usr/bin/apt-get update && /usr/bin/apt-get install nodejs -y"
}

exec { "AddGulp" :
	command => "/usr/bin/npm install -g gulp",
	require => Exec['AddNodePPA']
}

# Enable Apache mods
exec { "/usr/sbin/a2enmod rewrite" :
	unless => "/bin/readlink -e /etc/apache2/mods-enabled/rewrite.load",
	notify => Service[apache2],
	require => Package['apache2']
}
exec { "enable_apache_mods":
	command => ("/usr/sbin/a2enmod headers && /usr/sbin/a2enmod ssl && /usr/sbin/a2enmod proxy && /usr/sbin/a2enmod proxy_http"),
	notify => Service[apache2],
	require => Package['apache2']
}

# ---------------------------
#  Setup links and stuffs
# ---------------------------

# Set up a new VirtualHost
file { "/etc/apache2/sites-available/000-default.conf":
  ensure => "link",
  target => "/vagrant/manifests/assets/vhost.conf",
  require => Package["apache2"],
  notify => Service["apache2"],
  replace => yes,
  force => true,
}

# symlink project files to correct location
file { "/var/www/myapp":
  ensure => "link",
  target => "/vagrant/myapp",
  require => Package["apache2"],
  replace => yes,
  force => true,
}

# -----------------------------------------------
# Set Apache to run as the Vagrant user
# -----------------------------------------------

exec { "ApacheUserChange" :
  command => "/bin/sed -i 's/APACHE_RUN_USER=www-data/APACHE_RUN_USER=vagrant/' /etc/apache2/envvars",
  onlyif  => "/bin/grep -c 'APACHE_RUN_USER=www-data' /etc/apache2/envvars",
  require => Package["apache2"],
  notify  => Service["apache2"],
}

exec { "ApacheGroupChange" :
  command => "/bin/sed -i 's/APACHE_RUN_GROUP=www-data/APACHE_RUN_GROUP=vagrant/' /etc/apache2/envvars",
  onlyif  => "/bin/grep -c 'APACHE_RUN_GROUP=www-data' /etc/apache2/envvars",
  require => Package["apache2"],
  notify  => Service["apache2"],
}

exec { "apache_lockfile_permissions" :
  command => "/bin/chown -R vagrant:www-data /var/lock/apache2",
  require => Package["apache2"],
  notify  => Service["apache2"],
}

# -----------------------------------------------
# Misc
# -----------------------------------------------

# Use vim as git editor
exec { "GitConfig" :
  command => "/usr/bin/git config --global push.default simple && /usr/bin/git config --global core.autocrlf true && /usr/bin/git config --global core.editor 'vim'",
  require => [Package["vim"],Package["git"]]
}