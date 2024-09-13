import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImprimeEntrada
 */
@WebServlet()
public class ImprimeEntrada extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImprimeEntrada() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Restar entradas
		String diaAbono = request.getParameter("dias");
		if (diaAbono.equals("dos")) {
			Integer cant = (Integer) getServletContext().getAttribute("cantAbonosDosDias");
			cant--;
			getServletContext().setAttribute("cantAbonosDosDias", cant);
			System.out.println("los abonos para dos dias que quedan son: " + cant);
		} else {
			Integer cant = (Integer) getServletContext().getAttribute("cantAbonosTresDias");
			cant--;
			getServletContext().setAttribute("cantAbonosTresDias", cant);
			System.out.println("los abonos para tres dias que quedan son: " + cant);
		}

		// Crear Imagen
		OutputStream outputStream = response.getOutputStream();
		BufferedImage image = new BufferedImage(1500, 1000, BufferedImage.TYPE_INT_BGR);
		
		Graphics2D graphics = image.createGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, 1500, 1000);

		BufferedImage img = ImageIO.read(this.getServletContext().getResourceAsStream("/WEB-INF/img/logo.jpg"));
		int logoX = (1500 - img.getWidth()) / 2; 
		graphics.drawImage(img, logoX, 0, null);

		BufferedImage qr = generarQR(ganoPremio(request));
		int qrX = (1500 - 400) / 2; // QR size is 400x400, center it horizontally
		int qrY = 600; // Position it at the bottom part of the image
		graphics.drawImage(qr, qrX, qrY, 400, 400, null); // Set size to 400x400

		// Write the image as JPEG and close the output stream
		ImageIO.write(image, "jpeg", outputStream);
		outputStream.close();
	}

	private String ganoPremio(HttpServletRequest request) {
		String texto = "Entrada para " + request.getParameter("nombre") + request.getParameter("apellido") + " dni "
				+ request.getParameter("dni");
		if ((Math.random() * 50 + 1) > 25) {
			texto = texto
					+ "¡Felicitaciones! Te ganaste una remera. El día del evento pasá por el Stand TTPS y retirala con el QR";
		} else {
			texto = texto + "No te ganaste una remera, pero esperamos que disfrutes mucho el show";
		}

		return texto;
	}

	private BufferedImage generarQR(String texto) {
		Writer writer = new QRCodeWriter();
		try {
			BitMatrix bit = writer.encode(texto, BarcodeFormat.QR_CODE, 400, 400);
			return MatrixToImageWriter.toBufferedImage(bit);
		} catch (WriterException e) {
			return null;
		}
	}

}
