package ar.com.wolox.android.training.ui.home.news


import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.melnykov.fab.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home_news.*
import javax.inject.Inject

class HomeNewsFragment @Inject constructor() : WolmoFragment<BasePresenter<Any>>() {

    val title = "NEWS"

    private lateinit var newsList : RecyclerView
    private lateinit var fab : FloatingActionButton
    private var news:MutableList<HomeNews> = ArrayList()

    override fun layout(): Int = R.layout.fragment_home_news

    override fun init() {
        val home = HomeNews("Como cuidar los muebles de cuero","Hace un año, Marisa Bello, bibliotecóloga de La Plata, separada, 51 años, condujo un auto ultralujoso. Luego, durmió profundamente. Más tarde, se rió a carcajadas y olió un perfume indescriptible. Todo eso lo vivió desde una silla, apostada en el escenario de un pabellón de Tecnópolis. Para ella, sucedió durante una hora. \"En realidad, estuvo entre dieciséis y dieciocho minutos, que es el tiempo máximo que utilizamos durante nuestros espectáculos para hipnotizar a la gente -explica Gonzalo Blanc, un abogado de 41 años-. Pero la percepción del tiempo en ese estado es otra, y eso la llevó a sentir la experiencia mucho más larga\". Durante dieciséis presentaciones en Tecnópolis, Gonzalo, junto con el médico Daniel West, de 30 años, practicaron hipnosis colectiva sobre el público. Los dos viven en Montevideo y se dedican desde hace más de diez años a investigar las neurociencias. Dan seminarios, conferencias y talleres empresariales para mejorar el rendimiento a través de la hipnosis -tuvieron clientes como YPF, Telefónica, L'Oréal y Santillana-; practican hipnosis clínica para atenuar el dolor y curar patologías, y sus conferencias en TEDx Durazno y en TEDx Río de la Plata, llamadas \"¿Se puede entrenar a la mente para ser exitosos?\", tienen más de 150.000 reproducciones. " , "https://bucket1.glanacion.com/anexos/fotos/50/2082050.jpg")
        news.add(home)
        news.add(home)
        news.add(home)
        news.add(home)
        news.add(home)
        news.add(home)
        news.add(home)
        news.add(home)
        news.add(home)
        news.add(home)

        newsList = vHomeNewsRecyclerView
        fab = vFloatingActionButton

        newsList.layoutManager = LinearLayoutManager(context)
        newsList.adapter = HomeNewsAdapter(news, context!!)

        fab.attachToRecyclerView(newsList)
    }

}
