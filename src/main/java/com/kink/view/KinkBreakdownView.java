package com.kink.view;

import java.util.List;

import lombok.Data;

@Data
public class KinkBreakdownView {
	private KinksterView kinkster;
	private List<KinkView> compatibleKinks;
}
