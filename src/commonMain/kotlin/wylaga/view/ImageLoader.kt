package wylaga.view

import wylaga.view.display.displayables.Displayable
import wylaga.view.display.image.Base64Encoding

class ImageLoader(decode: (Base64Encoding) -> Displayable) {
    val player = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAACO0lEQVRoQ+2Z0W2EMAyG4fHG4bUDdAvm6Aydgy06QF8Zh8eezJ0jxziJ48RShcLLSeA4/vzbITnm6SbXfBOOaYBoldy27Q9s13V1TZqrcwAYIFrJ33auiqAaGJNnebmDrOt6cmzb5tonA6RUwqTJgyKeq5eLIhwCoaG8vGDcQLA3jp8XxuPz9evVKwMk1SO0rFANtAVVvMqrqyK8NyQQLK/evdIdhPcGV86rVwYIz3SprGifeJRXkyLCXirw8f7gIBSmx16sGiQXPAa0LMv0+72LC9vH1zLt+/UZrmZWKBWIJngatQWEjrdAJUFqg5dAIPv0ApVSimTeS9Gj1FFABEntlVKT8fugCF5YRtI9rT+0y71MLyCtEDCpF0hutYtAchA0OHAoNSxtdqsimnkkZQJIDQQPUioRCIjDSvd4b0m+cqsc9kwEgtsLjfMSTC0IV4IDpWBUICXnJRhtM1vnoWebU5EeDa4Nuqcd7ZUAIpVVz0m9fKEqRRD+loWActCSvQRR6yNlH0ByZSWdr0v2b9Ds1sfiA8ZIMJi4OWeQ2g5IgdQeYS0+crGKIJp/OrhTzRheYhYfEgzMfQHRBmQJogcIrrK0zC4gWogzoOM4v3tE1+OhOhaEMQ0+aCIjkCqIfwBClQkgmpXmkv2GbPZQBH3gonH2iAmEq1JbVhgJTYjBRx8QhDEEECkMMEYf/UC89h5KvxGI5ycxZTxNZgBTt1w2Tec7eID45rfe+1CkPme+I4Yivvmt934bRZ7EyeI6w5psEQAAAABJRU5ErkJgggAA"))
    val enemy = decode(PNG( "iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAhdEVYdENyZWF0aW9uIFRpbWUAMjAxNzoxMToyMiAxNToxMjo0OLQwW08AAAJtSURBVGhD7ZiBcYMwDEVDtwmrtHtkpg7SVcg4tPLxqSIkWzI2R7m+O84O2JK+ZDWkt39OxrCMLqZpmpdpYhzH0P4Se+y/LWMR6eQIIj5dQmBwHO/pM9G6GgS3CV9eMUUhR4kAtWKyQqSIaXqm8QjgyyvGFKJVguhZDSB9eMSoQjQRR1YDcJ8lMWp2abEmIloN6bR2v4xFs7OpiKU4EgTZ4EHwbFr2NSyfmg31aPEM1MIFEPJzLZaNFyGRbFlIG5/zkC5ODz+bivTMGtHLvnq0WsErIavSmlVIqdwtjkOUSEwvFbHKjvtHioGvUkzAfbQsg5yIUM9aj08Q7hErgFIGOVhTshUhdWAkiNyritwvG/wxvMYXsaXB3zhWIZ6NUUpCWoBXFv1ovS/XTh4fv4HzeTWZuMI9claGSH+Ekdn7WsaGoE9SRapEyCC1kvPANREeGwUQe6rIRohlUAZD6+geRg3Yyj23bGCvRKyjquzrESsAwAPJBZWz4cQnhJxYjnYGkMjZdtrv81eLMows8wv3O5DvEWTDco7nHAggPHNOyQ+ei737e+RE6BWRyExp2WxJ0N+lKhITQpmhizLGrxyRNbjgJ8C1eoQmodcUZNiTtV5rF9Z3rch/EM9Knx9WyGqJYA9YUEXsH1Z/kLUiNDarCqfi3HtBf6wVQZ/gQRUUsPdYEdH1Ai6CxvVoNRFzEFIEsU5A82PW+GhpIoiNEAJiOF36x4F2QqQIQhUi0YTd7+My++X5nNyCKUDLhkQLXOISIiFh3iBy2MkoBy6pEkLM821TpZ+v15C9FjbAtb4Qa+EZrc1kCxvERSpyu30DH8NuZmKFCV8AAAAASUVORK5CYII="))
    val redPlayerProjectile = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAQAAAAPCAYAAADDNm69AAAAH0lEQVQoU2P8P2HCfwYkwAgWKCiACE2YwDD8BJB8CwAfTUjSYtSVFQAAAABJRU5ErkJgggAA"))
    val greenSquareProjectile = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAcAAAAHCAYAAADEUlfTAAAASUlEQVQYV2NkgIKT/0/+h7HNGc0ZQWwwAZKYxzAPJseQxJDEAFLACJO4znAdLqnJoAlWgCJ5iOEQgx2DHQNWSZhWuCReO/G5FgCToyy40SBJMAAAAABJRU5ErkJgggAA"))

    val playerBaseChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAACDUlEQVRoQ+2YwXGEMAxF4ZhyuKaAdOE6UkPqcBcpIFfKyTE79kaMkGVLNtYuIeayM4uR9PQlYTxPF7nmi3BMA+RsSg5F/pUi3vsfDOycM6sAM8MBIIA45yKL934aIIo6NlMEygorEuKxUsUEhEJAQkN5WcGYgYAS3593jJe3+69VrwyQXP/hsgI1YG1Qxaq8uipCe4MDgfLq3SvdQWhvUOWsemWA0ExLZYX7xKK8DinC7KU2PtofFATDwL0jL8tqkFLwENCyLNPXx8oOttf3ZVrX9B5Ms1YoFYgmeBx1Cwh+vgUqC1IbPAcSso+voFJOkcJ7aXcrV34sSG6vlHNG/w+KwAVlxP2ntQfrSi/TBOQoRHBqBVKadjsQAYJC777+aGkdUET0wymzPVQJAXEWYeh0CkpxEwslIdeziR8KswOB7QWpXWmysTBc0AJItR/8SSCBSMZFZZQN3eQnAenR4MqAuy7D5RUzgU87unp6gDFQRQShb9kQW6aXYtjceo6n1kZu/QZSKivu+1pa/wtarPkWG7mqgcTNpQW57QAXSO0nbIuNUqwsiOakgxrVPENLrMUGBxN8JyDagFqC6AHCDacERAvxgGEkuqDnypsifwkC7YbjIfmmiGbSiCl60gIYGlGRAfIkFbDbaypy5BjmBKLEvaJ2+3yGeIsxDJCzSTQUGYoYZWCUllFim83eAMSCqDoeVT/pAAAAAElFTkSuQmCC"))
    val playerHurtChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAACEElEQVRoQ+2YwW3DMAxF5VvXcY8doFNUc3SGzuFO0QF6TNbpLQUd0KBpiqJkMXBd+RIgYaj/9ElJ1hBO8gwn4Qgd5GhOdkf+lSPTNN0ocIzRrQLcEgMAgLzFOLN8TlPoIIY6dnMEy4o6Anq8XHEB4RA4oVBeXjBuIOjEz9cd4+n1/unVKx0k1X+0rNANjAVXvMqrqSO8NyQQLK/WvdIchPcGd86rVzoIn+lcWdE+8SivXY7wsxSWFQjl/cFBKAz+tmezLAbRxKOg53EM3x9XcWF7eR/D5br9DVezWigTiEU8VV0DQv9fA5UEKRUvgcDs0wdcSjmS2pesUCJI6qxkOITOIeAIPlhG0nfWfBinbaYbkL0QniDaarcC0SCGsL5xuYWwevvjpVXriGUcyZkFpAQCReZg+OoE5SWtWJiPQ2jjcJgVCN0HcslzMJJoDSQFkYPBvUcFySXPwVibuXYc+m4zg7RocKvolnG0vBYQqaxaDuqVC13JgvANCQRp0FK8BFGaIxW/gGhlJb1f5+JBdO7wV5OD3pFJx5lBC0gJkoSUvsLW5NC0iiCWmw6e1PIfXmI1OSQYGHsDYhVUI6IFCK6ytGc2IFYIrxWoJC+/V14c+UsQCIwwiyOWlaZkth4Zi4vG7EgHeeTUJ8Y6pyO5nfgAE69KAFdMtyhHBwF9HeRoLnVHuiNOM9BLy2liq9P+AhJ7sDqaq0RRAAAAAElFTkSuQmCC"))
    val playerDireChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAACmElEQVRoQ+2YS07DMBCGnSWLnINKrLJAqjgApyAXKAfgDByAXiCcggMgJBZdIZVzZNFl0LgedzKZ2E4aq0lxNqCkHs83v+eRZOpKruxKOFQCmZuSSZF/pUhVVQ0FLssy2gmIZhgAAKQsS81SVZVKIAHnOJoi+zxvbu9qlX0dvQBF4IqlShQQDoEBjQkTBaRZq+b3J1erulaHjyPGzeNJmRiqRAGhSb5YECy5UK0QAo8WqBLreE2qCIUA5yWQWIk/OQj2DQ5BVYnRVxII71G+YyUpMmVfmUwRWqmk/OAgNFfw2TlleTAIHQTZxnZALIpCfb7uxMHi4aVQu1332bmzWBAIn2LvNxtlxg+9Hhog/MVxZCjIPs+1PWND23QETAxQL4gwglsDzVo7rdfiOIKdHO4hCESfXqCSpAjYM+utP2AXJgPsO77jJ4LwxJVCQKNIIRAE1+AxAjh+j9oFe6u6zqTA4O9czbQDEgIhaktuSk6HgOC0bBQXt+mDaYF4IDh06+2P7joGhKx37oPKcUq7aCAE2nHC8OoEgFLFckB09qH5aUq+ZmiB4HjBaH2VTYSRnPaABO1Dc+l7u7Uvaj4Qn3GvMr58Ms8H7QOqwPX+fPoO0AKB/gAlbykXTfxWJOib3ZJgYMLogOjEMR8M4H/ekOBeTy5pdun3UlCG2uj7PY42GVarp7cSu6vdV5p/XNUt9O1vjA0+lPImqUEkWtcQJzkSCkEc0NWO7u2z4fJVBAmZRLnRkDX8iI2xIcHA3h2QUIfGODEFiMnD1inqgIRCzKGi8e/KVpElQdA8gxyzipikC+2ucxCEVtbjSx2pQAnkkhKhENelyDmfYS6pBk36ReaFFLwEMocjRX1IiiRFIkUgHa1IgR1t9g8sG+s6aH7n+AAAAABJRU5ErkJgggAA"))
    val playerHealChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAACFElEQVRoQ+2YMXbDIAyG7bHHScbuvUV8jp6h53Bu0b1jcpyM7pNj8UAIIWP04rp4TGTxf/wSYPruIE9/EI6ugezNyebIv3JkHMfJBx6GwawCzBIDAIAMl8vMMl6vXQNR1LGZI1hWviOgx8oVExAKgRMK5WUFYwaCTjy+n0O8fTz73qpXGkiq//yyQjcwFlyxKq+qjtDe4ECwvGr3SnUQ2hvUOateaSB0pnNl5feJRXltciQ6Sy3HERBK+4OC+DD435bNcjWIJB4Fnc7n7ufrzi5s75+n7n67Rf/halYKpQLRiPeVlYD475dAJUHWiudAYPb9B1xKOZLcl5ZjTc4pFiR1VlIcQucQcAQfLCPuN20+jJM20whkK4QliLTaBSAiRN+H0NMUfP3R0ip2RDEO54wTtwoCVWZg6OoE5cWtWG4SKIQwDoUJQPB4EdRuKnkGhhMtghSM438SyCC55ApnVA1dOE4EUqPBVYIrB/nlNTvi33ZUHss8HbqSBaG7LChje2mRzMVzNGtzpOIdiFRW3Pd1Ln4GzVzEleRIVQ1OXC8FpARxQtZ+wpbkkLSyIJqbDppU8w4tsZIcHAyMHYFoBZWIqAHCLU4RiBbCfClSDEDvlZ0jfwnCnYaXS3LniGalUUzSS0Jw0ZgdaSAv8SAc9JiO5HbiHUy8KAFcUd2i7B0E9DWQvbnUHGmOGM1AKy2jiS1O+wvROsA65DQ76AAAAABJRU5ErkJgggAA"))

    val playerBaseSpecial = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAACQAAAAECAYAAADmrJ2uAAAAOklEQVQoU2NkQAKGhob/kfnnz59nROZTi43PHriF6IpgllPbUYTsATsIlyJqO4oYexgJKaKWo4i1BwCsKx2XCVZoUgAAAABJRU5ErkJgggAA"))
    val playerBoostSpecial = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAACQAAAAECAYAAADmrJ2uAAAAS0lEQVQoU2NkQAKGhob/z59bAxYxNAphOH/+PCOyPLXY+OyBWwhTBHIICIAcRgtHEbIH7CBkRSghRGVHEWMPI3Lw4YoSaoQUsfYAALVdR5fmYNrmAAAAAElFTkSuQmCC"))

    private class PNG(data: String): Base64Encoding("data:image/png;base64,", data)
}