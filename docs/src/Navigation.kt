import image_path.*
import kotlin.dom.addClass
import kotlin.dom.hasClass
import kotlin.dom.removeClass
import org.w3c.dom.asList
import kotlin.browser.document
import kotlin.math.log

class Navigation {
    private fun toggleNavigationVisibility() = navItems()?.apply {
        if (hasClass(IS_VISIBLE)) {
            removeClass(IS_VISIBLE)
            addClass(INVISIBLE)
        } else {
            addClass(IS_VISIBLE)
            removeClass(INVISIBLE)
        }
    }

    fun getAllNavElements() {
        document.querySelectorAll("[data-nav='home']").asList().forEach {
            it.addEventListener("click", {
                goHome()
            })
        }

        document.querySelectorAll("[data-nav='art']").asList().forEach {
            it.addEventListener("click", {
                viewArt()
            })

        }

        document.querySelectorAll("[data-nav='mobile']").asList().forEach {
            it.addEventListener("click", {
                viewMobile()
            })

        }
    }

    fun setUpHamburger() = hamburger()?.apply {
        getAllNavElements()
        addEventListener("click", {
            if (this.hasClass(IS_ACTIVE)) this.removeClass(IS_ACTIVE) else this.addClass(IS_ACTIVE)
            toggleNavigationVisibility()
        })
    }


}


fun goHome() {
    portfolio()?.hide()
    mobilePortfolio()?.hide()
    destroyPortfolio()
    index()?.show()
}

fun viewArt() {
    index()?.hide()
    mobilePortfolio()?.hide()
    destroyPortfolio()
    showPortfolioItems()
    portfolio()?.show()
}

fun viewMobile() {
    index()?.hide()
    portfolio()?.hide()
    destroyPortfolio()
    mobilePortfolio()?.show()
    showMobileItems()
}

fun destroyPortfolio() {
    albumArt()?.defenestrate()
    cards()?.defenestrate()
    cardsPortrait()?.defenestrate()
    logos()?.defenestrate()
    mobilePortfolioTarget()?.defenestrate()
}

fun showMobileItems() {
    mobilePortfolioTarget()?.appendChild(headerWithText("Mobile Applications"))
    MobileApp.MobileAppImages.values().forEach {
        mobilePortfolioTarget()?.appendChild(Card().let { card ->
            card.buildCard("Card", "Card-art", withBackground = true, imageURL = it.value)
            card.container
        })
    }

    mobilePortfolioTarget()?.appendChild(headerWithText("Ipad App"))
    MobileApp.IpadAppImages.values().forEach {
        mobilePortfolioTarget()?.appendChild(Card().let { card ->
            card.buildCard("Card", "Card-art", withBackground = true, imageURL = it.value)
            card.container
        })
    }
}

fun showPortfolioItems() {
    albumArt()?.appendChild(headerWithText("Album Art"))
    AlbumArt.values().forEach {
        albumArt()?.appendChild(Card().let { album_art ->
            album_art.buildCard("Card", "Card-albumArt", withBackground = true, imageURL = it.value)
            album_art.container
        })
    }

    cards()?.appendChild(headerWithText("Business Cards & Logo Design"))
    BusinessCardsLandscape.values().forEach {
        cards()?.appendChild(Card().let { bizCard ->
            bizCard.buildCard("Card", "Card-businessCards", "Card-businessCards--landscape", withBackground = true,
                    imageURL = it.value)
            bizCard.container
        })
    }

    BusinessCardsPortrait.values().forEach {
        cardsPortrait()?.appendChild(Card().let { bizCard ->
            bizCard.buildCard("Card", "Card-businessCards", "Card-businessCards--portrait", withBackground = true,
                    imageURL = it.value)
            bizCard.container
        })
    }

//    logos()?.appendChild(headerWithText("Logo Design"))
    Logos.values().forEach {
        logos()?.appendChild(Card().let { logo ->
            logo.buildCard("Card", "Card-art", withBackground = false, imageURL = it.value)
            logo.container
        })
    }

}

fun headerWithText(text: String) = document.createElement("h1").apply {
    innerHTML = text
    addClass("Portfolio-title", "shaded", "Space-small")
}