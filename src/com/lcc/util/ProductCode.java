package com.lcc.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductCode {

	public static  void productCode( HttpServletRequest request, HttpServletResponse response )
	{
		response.setContentType("image/jpeg");
		response.setHeader("Cache-Control", "no-cache,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Last-Modified", new Date().toLocaleString());
		response.setDateHeader("Expires",  0 );
		int width = 60, height = 20;
		
		BufferedImage image = new BufferedImage( width, height , BufferedImage.TYPE_INT_RGB );
		Graphics g = image.getGraphics();
		g.setColor(new Color( 0xDCDCDC ));
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black );
		
		Random r = new Random( );
		int rst = 0;
		while( ( rst = r.nextInt(10000) ) < 0 ){
			
		}
		
		String rand = rst+"";
		switch( rand.length() ){
		case 1:
			rand ="000"+rand;
			break;
			
		case 2:
			rand ="00"+rand;
			break;
		case 3:
			rand ="0"+rand;
			break;
			
		default:
			rand = rand.substring(0,4);
			break;
			
		}
		
		request.getSession().setAttribute("rand", rand );
		g.setColor(Color.black);
		g.setFont(new Font( "Atlantic Inline", Font.PLAIN, 18 ));
		g.drawString(rand.charAt(0)+"",  8 , 17 );
		g.drawString(rand.charAt(1)+"",  20 , 15 );	
		g.drawString(rand.charAt(2)+"",  35 , 18 );
		g.drawString(rand.charAt(3)+"",  45 , 15 );
		
		Random random = new Random( );
		
		for( int i = 0 ; i< 5; i++ ){
			int x = random.nextInt(width);
			
			int y = random.nextInt(height );
			
			g.drawOval(0, 0, x, y);
			
		}
		
		g.dispose(); 
		
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream() );
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
