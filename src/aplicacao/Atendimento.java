package aplicacao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class Atendimento {
	private static class Atende {
		public int cartao;
		public String nome;
		public String sobreNome;
		double valor;
		double taxaAtraso;
		double bonus;
		public Atende prox;
	}

	public static void main(String[] args) {

		Atende inicio = null;
		Atende fim = null;
		Atende aux;
		int op = 0;
		do {
			try {
				op = mostrarMenu();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"A tecla <<Cancelar>> foi acionada!\nEscolha a opção 6 para encerrar.", "Mensagem",
						JOptionPane.CLOSED_OPTION);
				continue;
			}
			if (op < 1 || op > 13) {
				System.out.println("Opção Inválida!!");
			}
			if (op == 1) {
				int numero = 0;
				try {
					numero = Integer.parseInt(JOptionPane.showInputDialog("NÚMERO DO CARTÃO: ", "0"));
				} catch (NumberFormatException e) {
					agenteDeErro(e);
					continue;
				}
				aux = inicio;
				boolean achou = false;
				while (aux != null) {
					if (aux.cartao == numero) {
						achou = true;
						JOptionPane.showMessageDialog(null, "Esse número do cartão já foi usado.\nFavor verificar!");
						break;
					}
					aux = aux.prox;
				}
				if (achou == false) {
					Atende novo = new Atende();
					novo.cartao = numero;

					try {
						novo.nome = JOptionPane.showInputDialog("NOME: ", "");
						novo.sobreNome = JOptionPane.showInputDialog("SOBRENOME: ", "");
						novo.valor = Double.parseDouble(JOptionPane.showInputDialog("VALOR: ", "0"));
					} catch (NumberFormatException e) {
						agenteDeErro(e);
						continue;
					}

					if (inicio == null) {
						inicio = novo;
						fim = novo;
					} else {
						fim.prox = novo;
						fim = novo;
					}
					JOptionPane.showMessageDialog(null,
							"O cartão número " + novo.cartao + ", foi inserido para atendimento: ",
							"MENSAGEM DO PROGRAMA", JOptionPane.CLOSED_OPTION);
				}
			}
			if (op == 2) {

			}
			if (op == 3) {

			}
			if (op == 4) {

			}
			if (op == 5) {

			}
			if (op == 6) {

			}
			if (op == 7) {

			}
			if (op == 8) {

			}
			if (op == 9) {

			}
			if (op == 10) {

			}
			if (op == 11) {

			}
			if (op == 12) {

			}
		} while (op != 13);
		System.out.println("-------------------------------------------");
		System.out.println("Programa finalizado em: " + getDateTime());
		System.out.println("-------------------------------------------");
		JOptionPane.showMessageDialog(null, "PROGRAMA FINALIZADO!");
		
	}

	private static void agenteDeErro(NumberFormatException e) {
		JOptionPane.showMessageDialog(null,
				"ERRO AO TENTAR CONVERTER UM VALOR\nFAVOR VERIRIFICAR\nMENSAGEM ORIGINAL: " + e.getMessage(),
				"AGENTE DE ERRO", JOptionPane.CLOSED_OPTION);
	}

	private static void atendimentoVazio() {
		JOptionPane.showMessageDialog(null, "NÃO HÁ ATENDIMENTOS", "MENSAGEM DO PROGRAMA", JOptionPane.CLOSED_OPTION);
	}

	private static int mostrarMenu() {
		String texto = "\nMENU DE OPÇÕES\n" + "\n1  - Recepcionar cliente"
				+ "\n2  - Consultar clientes a serem atendidos" + "\n3  - Atender cliente"
				+ "\n4  - Liberar todos os clientes" + "\n5  - Verificar quantidade de clientes a atender"
				+ "\n6  - Localizar cliente por número" + "\n7  - Localizar cliente por nome"
				+ "\n8  - Emitir relatório de clientes" + "\n9  - Ver relatório de clientes"
				+ "\n10 - Filtrar clientes por valor" + "\n11 - Ver endereços hash" + "\n12 - Sobre" + "\n13 - Sair\n";
		int opcao = Integer.parseInt(JOptionPane.showInputDialog(texto, "1"));
		return opcao;
	}

	public static String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
