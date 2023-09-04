package com.srg.mc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.srg.mc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		pagto.setDataVencimento(calendar.getTime());
	}
}
