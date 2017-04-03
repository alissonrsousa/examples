package br.com.alissonrsousa.resource.impl;

import br.com.alissonrsousa.resource.TesteResource;

public class TesteResourceImpl implements TesteResource {

	@Override
	public String teste() throws Exception {
		return "teste";
	}

}
