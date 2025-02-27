import java.io.File

class HTML {

    private val children = mutableListOf<String>()
    fun head(x: Head.() -> Unit){
        val head= Head()
        head.x()
        children.add("<head>${head.content}</head>")

    }

    fun producto(init: Producto.() -> Unit) {
        val producto = Producto()
        producto.init()
        children.add("<producto>${producto.content}</producto>")
    }

    override fun toString(): String {
        return "<html>"+"\n"+children.joinToString("\n")+"\n"+"</html>"
    }
}
class Head{
    var content: String = ""

    fun title(text: String) {
        content += "<title>$text</title>"
    }


}
class Producto {
    var content: String = ""

    fun nombre(text: String) {
        content += "<nombre>$text</nombre>"
    }

    fun precio(number: Int) {
        content += "<precio>$number</precio>"
    }
    fun estado(text: String) {
        content += "<h1>$text</h1>"
    }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML() //crea el objeto receptor
    html.init()// incializamos con la lambda el objeto receptor
    return html
}

fun main() {
    val myHTML = html {
        head {
            title("Mi pagina hecha con MI DSL")
        }
        producto{
            nombre("Camion")
            precio(10000)
            estado("roto")
        }
    }

    File("fichero.html").writeText(myHTML.toString())
}
