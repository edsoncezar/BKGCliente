package br.com.gc.relatorio;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import br.com.gc.dao.Cliente;
import br.com.gc.dao.ClienteDAO;
import br.com.gc.utils.Componentes;
import br.com.gc.vo.ClienteVO;
import br.com.gc.vo.ProdutoVO;


public class RelatorioPersil {


	public RelatorioPersil() throws BiffException, IOException {

		Componentes componentes = new Componentes();

		WritableWorkbook workbook = Workbook.createWorkbook(new File(componentes.saveRecordXLS()));

		WritableFont arial = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,true);
		WritableCellFormat format = new WritableCellFormat(arial);

		WritableSheet sheet = workbook.createSheet("PERSIL", 0);

		Cliente cliente = new ClienteDAO();
	    List<ClienteVO> listAllItemCliente = cliente.getAllItemCliente();

	    int countLine = 0;

	    Label labelName = new Label(0,countLine,"Nome",format);
		Label labelCic = new Label(1,countLine,"Cic",format);
		Label labelEnd = new Label(2,countLine,"Endereço",format);
		Label labelBair = new Label(3,countLine,"Bairro",format);
		Label labelCid = new Label(4,countLine,"Cidade",format);
		Label labelEst = new Label(5,countLine,"Estado",format);
		Label labelDdd = new Label(6,countLine,"DDD",format);
		Label labelTel = new Label(7,countLine,"Telefone",format);
		Label labelCel = new Label(8,countLine,"Celular",format);

		try {
			sheet.addCell(labelName);
			sheet.addCell(labelCic);
			sheet.addCell(labelEnd);
			sheet.addCell(labelBair);
			sheet.addCell(labelCid);
			sheet.addCell(labelEst);
			sheet.addCell(labelDdd);
			sheet.addCell(labelTel);
			sheet.addCell(labelCel);
		} catch (RowsExceededException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	    for (Iterator iter = listAllItemCliente.iterator(); iter.hasNext();) {
			ClienteVO clienteVO = (ClienteVO) iter.next();

			countLine+=2;

	    try {

			Label label = new Label(0,countLine,clienteVO.getNome());
			Label labelOne = new Label(1,countLine,clienteVO.getCic());
			Label labelTwo = new Label(2,countLine,clienteVO.getEndereco());
			Label labelThree = new Label(3,countLine,clienteVO.getBairro());
			Label labelFour = new Label(4,countLine,clienteVO.getCidade());
			Label labelFive = new Label(5,countLine,clienteVO.getEstado());
			Label labelSix = new Label(6,countLine,clienteVO.getDdd());
			Label labelSeven = new Label(7,countLine,clienteVO.getTelefone());
			Label labelEight = new Label(8,countLine,clienteVO.getCelular());

			sheet.addCell(label);
			sheet.addCell(labelOne);
			sheet.addCell(labelTwo);
			sheet.addCell(labelThree);
			sheet.addCell(labelFour);
			sheet.addCell(labelFive);
			sheet.addCell(labelSix);
			sheet.addCell(labelSeven);
			sheet.addCell(labelEight);

			countLine+=2;

			if(! clienteVO.getListaItens().isEmpty()){

			Label labelQuant = new Label(0,countLine,"Quantidade",format);
			Label labelProdu = new Label(1,countLine,"Produto",format);
			Label labelValor = new Label(2,countLine,"Valor",format);

			sheet.addCell(labelQuant);
			sheet.addCell(labelProdu);
			sheet.addCell(labelValor);
			}

			for (Iterator iterator = clienteVO.getListaItens().iterator(); iterator
					.hasNext();) {
				ProdutoVO produto = (ProdutoVO) iterator.next();

				countLine++;

				jxl.write.Number number = new jxl.write.Number(0, countLine,produto.getQuantidade());
				Label labelItemOne = new Label(1,countLine,produto.getProduto());
				Label labelItemFive = new Label(2,countLine,produto.getValor());

				sheet.addCell(number);
				sheet.addCell(labelItemOne);
				sheet.addCell(labelItemFive);

			}

			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			workbook.write();
			workbook.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      System.out.println("Relatório finalizado !");

	}

	public static void main(String[] args) {

		try {
			RelatorioPersil sheet = new RelatorioPersil();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
