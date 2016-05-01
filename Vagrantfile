# /Vagrantfile
VAGRANTFILE_API_VERSION = "2"
Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
	config.vm.box = "ubuntu/trusty64"
	config.vm.network "private_network", ip: "192.168.33.110"
	
	#config.ssh.username = "marc"
	#config.ssh.forward_agent = "true"
	#config.ssh.private_key_path = "D:/Users/UserName/.ssh/id_rsa"
	
	# Enable provisioning with Puppet stand alone. Puppet manifests
	# are contained in a directory path relative to this Vagrantfile.
	# You will need to create the manifests directory and a manifest in
	# the file default.pp in the manifests_path directory.
	#
	config.vm.provision "puppet" do |puppet|
		puppet.manifests_path = "manifests"
		puppet.manifest_file = "site.pp"
	end
end
