import groovy.io.FileType
import static java.awt.RenderingHints.*
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

int Width = 2000
int Height = 1000

int thumbWidth = 256
int thumbHeight = 256

def folder = "images"

new File("compressed").mkdir();
new File("compressed/thumb").mkdir();
int count = 1;
new File("$folder").eachFile(FileType.FILES) {
BufferedImage img = ImageIO.read(it)
//make picture
new BufferedImage( Width, Height, img.type ).with { i ->
  createGraphics().with {
    setRenderingHint( KEY_INTERPOLATION, VALUE_INTERPOLATION_BICUBIC )
	setRenderingHint(KEY_RENDERING,	VALUE_RENDER_QUALITY)
	setRenderingHint(KEY_COLOR_RENDERING, VALUE_COLOR_RENDER_QUALITY)
	setRenderingHint(KEY_ALPHA_INTERPOLATION, VALUE_ALPHA_INTERPOLATION_QUALITY)
	setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON)
    drawImage( img, 0, 0, Width, Height, null )
    dispose()
  }
  ImageIO.write( i, "jpg", new File( "compressed/${count}.jpg" ) )
}
//make thumb
new BufferedImage( thumbWidth, thumbHeight, img.type ).with { i ->
  createGraphics().with {
    setRenderingHint( KEY_INTERPOLATION, VALUE_INTERPOLATION_BICUBIC )
	setRenderingHint(KEY_RENDERING,	VALUE_RENDER_QUALITY)
	setRenderingHint(KEY_COLOR_RENDERING, VALUE_COLOR_RENDER_QUALITY)
	setRenderingHint(KEY_ALPHA_INTERPOLATION, VALUE_ALPHA_INTERPOLATION_QUALITY)
	setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON)
    drawImage( img, 0, 0, thumbWidth, thumbHeight, null )
    dispose()
  }
  ImageIO.write( i, "jpg", new File( "compressed/thumb/${count}.jpg" ) )
}
count++;
}
