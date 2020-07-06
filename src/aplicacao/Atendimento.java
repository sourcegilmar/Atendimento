package aplicacao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Atendimento {
	private static class Atende {
		public int cartao;
		public String nome;
		public String sobreNome;
		double valor;
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
				if (inicio == null) {
					atendimentoVazio();
				} else {
					JTextArea saida = new JTextArea(6, 45); // HEIGHT X WIDTH
					JScrollPane scroll = new JScrollPane(saida);
					saida.append("CARTÃO\t" + "NOME\t" + "SOBRENOME\t" + "VALOR\n");
					saida.append("-------------------------------------------------------------------------\n");
					aux = inicio;
					while (aux != null) {
						saida.append(aux.cartao + "\t" + aux.nome + "\t" + aux.sobreNome + "\t" + aux.valor + "\n");
						aux = aux.prox;
					}
					saida.append("\n");
					JOptionPane.showMessageDialog(null, scroll, "CONSULTAR DADOS DO ATENDIMENTO",
							JOptionPane.CLOSED_OPTION);
				}
			}
			if (op == 3) {
				if (inicio == null) {
					atendimentoVazio();
				} else {
					JOptionPane.showMessageDialog(null,
							"CARTÃO:  " + inicio.cartao + ", NOME: " + inicio.nome + " foi atendido(a)!",
							"MENSAGEM DO PROGRAMA", JOptionPane.CLOSED_OPTION);
					inicio = inicio.prox;
				}
			}
			if (op == 4) {
				if (inicio == null) {
					atendimentoVazio();
				} else {
					inicio = null;
					JOptionPane.showMessageDialog(null, " * * O ATENDIMENTO FOI LIBERADO * *", "MENSAGEM DO PROGRAMA",
							JOptionPane.CLOSED_OPTION);
				}
			}
			if (op == 5) {
				aux = inicio;
				int n = 0;
				double soma = 0;
				while (aux != null) {
					soma = soma + aux.valor;
					aux = aux.prox;
					n++;
				}
				JOptionPane.showMessageDialog(null,
						"O ATENDIMENTO CONTÉM: " + n + " ELEMENTOS.\nVALOR TOTAL: " + soma + "\n",
						"MENSAGEM DO PROGRAMA", JOptionPane.CLOSED_OPTION);
			}
			if (op == 6) {
				int cartao = Integer.parseInt(JOptionPane.showInputDialog("Informe o número do cartão", "0"));
				aux = inicio;
				int posicao = 1;
				while (aux != null) {
					if (cartao == aux.cartao) {
						String texto = "CARTÃO: " + aux.cartao + "\n" + "NOME: " + aux.nome + "\n" + "SOBRENOME: "
								+ aux.sobreNome + "\n" + "VALOR: " + aux.valor + "\n" + "POSIÇÃO: " + posicao
								+ "a. POSIÇÃO";
						JOptionPane.showMessageDialog(null, "DADOS DO CLIENTE: \n\n" + texto, "MENSAGEM DO PROGRAMA",
								JOptionPane.CLOSED_OPTION);
					}
					posicao++;
					aux = aux.prox;
				}

			}
			if (op == 7) {
				String nome = JOptionPane.showInputDialog("Nome do cliente", "");
				aux = inicio;
				int posicao = 1;
				while (aux != null) {
					if (aux.nome.equals(nome)) {
						String texto = "CARTÃO: " + aux.cartao + "\n" + "NOME: " + aux.nome + "\n" + "SOBRENOME: "
								+ aux.sobreNome + "\n" + "VALOR: " + aux.valor + "\n" + "POSIÇÃO: " + posicao
								+ "a. POSIÇÃO";
						JOptionPane.showMessageDialog(null, "DADOS DO CLIENTE: \n\n" + texto, "MENSAGEM DO PROGRAMA",
								JOptionPane.CLOSED_OPTION);
					}
					posicao++;
					aux = aux.prox;
				}

			}
			if (op == 8) {
				if (inicio == null) {
					atendimentoVazio();
				} else {
					aux = inicio;

					try {
						FileWriter arq = new FileWriter("c:\\Dados\\Atendimento.txt");
						PrintWriter gravar = new PrintWriter(arq);

						while (aux != null) {

							gravar.printf("%d, %s, %s, %.2f %n", aux.cartao, aux.nome, aux.sobreNome, +aux.valor);
							aux = aux.prox;
						}
						gravar.printf("%s %n", "--------------------------");
						gravar.printf("%s %n", "copyright (c) by: Fulano de Tal, Sicrano de Tal");
						arq.close();
					} catch (IOException e) {
						System.out.println("MENSAGEM / CLASS ArquivoTexto:\nErro ao tentar gravar no arquivo");
					}

					JOptionPane.showMessageDialog(null, "ARQUIVO GRAVADO COM SUCESSO", "MENSAGEM DO SISTEMA",
							JOptionPane.CLOSED_OPTION);
				}
			}
			if (op == 9) {
				int resposta = JOptionPane.showConfirmDialog(null, "DESEJA VER ARQUIVO?", "MENSAGEM",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					try {
						Process pro = Runtime.getRuntime().exec("cmd.exe /c  c://Dados//Atendimento.txt");
						pro.waitFor();
					} catch (Exception e) {
						System.out.println("Erro . . . ");
					}
				}
			}
			if (op == 10) {
				double filtro = Double.parseDouble(
						JOptionPane.showInputDialog("FILTRAR ATENDIMENTOS PARA VALORES SUPERIORES A: ", ""));
				JTextArea saida = new JTextArea(6, 45); // HEIGHT X WIDTH
				JScrollPane scroll = new JScrollPane(saida);
				saida.append("CARTÃO\t" + "NOME\t" + "SOBRENOME\t" + "VALOR\n");
				saida.append("----------------------------------------------------------------------------\n");
				aux = inicio;
				int posicao = 1;
				while (aux != null) {
					if (aux.valor > filtro) {
						saida.append(aux.cartao + "\t" + aux.nome + "\t" + aux.sobreNome + "\t" + aux.valor + "\n");
					}
					posicao++;
					aux = aux.prox;
				}
				saida.append("\n");
				JOptionPane.showMessageDialog(null, scroll, "ATENDIMENTOS COM VALORES SUPERIORES A: " + filtro,
						JOptionPane.CLOSED_OPTION);
			}
			if (op == 11) {
				if (inicio == null) {
					atendimentoVazio();
				} else {
					JTextArea saida = new JTextArea(7, 45); // HEIGHT X WIDTH
					JScrollPane scroll = new JScrollPane(saida);
					saida.append("NOME\t" + "ENDEREÇO\tPROX\t\n");
					saida.append("-------------------------------------------------------------\n");
					aux = inicio;
					while (aux != null) {
						if (aux.prox != null)
							saida.append(aux.nome + "\t" + aux.hashCode() + "\t" + aux.prox.hashCode() + "\n");
						else
							saida.append(aux.nome + "\t" + aux.hashCode() + "\tfim\n");

						aux = aux.prox;
					}
					saida.append("\n");
					JOptionPane.showMessageDialog(null, scroll, "CONSULTAR DADOS DO ATENDIMENTO",
							JOptionPane.CLOSED_OPTION);
				}

			}
			if (op == 12) {
				JTextArea saida = new JTextArea(8, 30); // HEIGHT X WIDTH
				saida.append("\n");
				saida.append("PROGRAMA DE ATENDIMENTO AO CLIENTE\n");
				saida.append("-------------------------------------------------------------\n");
				saida.append("Copyright (c) Byta Bug Informática Ltda\n");
				saida.append("Programadores: Asdrubal, Indalécio e Quelé\n");
				saida.append("Versão 1.0\n");
				saida.append("Data: Maio de 2017\n");
				saida.append("\n");
				JOptionPane.showMessageDialog(null, saida, "SOBRE O PROGRAMA", JOptionPane.CLOSED_OPTION);
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
