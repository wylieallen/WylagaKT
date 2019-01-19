package wylaga.view

import wylaga.view.display.displayables.Displayable
import wylaga.view.display.image.Base64Encoding

class ImageLoader(decode: (Base64Encoding) -> Displayable) {
    val redPlayerProjectile = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAQAAAAPCAYAAADDNm69AAAAH0lEQVQoU2P8P2HCfwYkwAgWKCiACE2YwDD8BJB8CwAfTUjSYtSVFQAAAABJRU5ErkJgggAA"))
    val orangePlayerProjectile = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAQAAAAPCAYAAADDNm69AAAAH0lEQVQoU2P8f3DCfwYkwAgWuFgAEdKfwDD8BJB8CwDHfkux89QabwAAAABJRU5ErkJgggAA"))
    val yellowPlayerProjectile = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAQAAAAPCAYAAADDNm69AAAAHUlEQVQoU2P8/3/CfwYkwAgRKIAKTWAYhgII7wIAJyZCU9+uA9UAAAAASUVORK5CYIIA"))
    val greenPlayerProjectile = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAQAAAAPCAYAAADDNm69AAAAIUlEQVQoU2M8+H/CfwYkwAgSsGcoAAsdZJjAMOwEkH0LAJWMS7FHB+sLAAAAAElFTkSuQmCC"))
    val cyanPlayerProjectile = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAQAAAAPCAYAAADDNm69AAAAH0lEQVQoU2M8+P//fwYkwAgSsIcKHGRgYBh2Asi+BQCj5EUy76TnHAAAAABJRU5ErkJgggAA"))
    val magentaPlayerProjectile = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAQAAAAPCAYAAADDNm69AAAAHUlEQVQoU2P83/D/PwMSYAQLNEBFGhgYhp8Akm8BtnZBY+mdcMUAAAAASUVORK5CYIIA"))
    val greenSquareProjectile = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAcAAAAHCAYAAADEUlfTAAAASUlEQVQYV2NkgIKT/0/+h7HNGc0ZQWwwAZKYxzAPJseQxJDEAFLACJO4znAdLqnJoAlWgCJ5iOEQgx2DHQNWSZhWuCReO/G5FgCToyy40SBJMAAAAABJRU5ErkJgggAA"))

    // Player ship:
    val playerBaseChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAACDUlEQVRoQ+2YwXGEMAxF4ZhyuKaAdOE6UkPqcBcpIFfKyTE79kaMkGVLNtYuIeayM4uR9PQlYTxPF7nmi3BMA+RsSg5F/pUi3vsfDOycM6sAM8MBIIA45yKL934aIIo6NlMEygorEuKxUsUEhEJAQkN5WcGYgYAS3593jJe3+69VrwyQXP/hsgI1YG1Qxaq8uipCe4MDgfLq3SvdQWhvUOWsemWA0ExLZYX7xKK8DinC7KU2PtofFATDwL0jL8tqkFLwENCyLNPXx8oOttf3ZVrX9B5Ms1YoFYgmeBx1Cwh+vgUqC1IbPAcSso+voFJOkcJ7aXcrV34sSG6vlHNG/w+KwAVlxP2ntQfrSi/TBOQoRHBqBVKadjsQAYJC777+aGkdUET0wymzPVQJAXEWYeh0CkpxEwslIdeziR8KswOB7QWpXWmysTBc0AJItR/8SSCBSMZFZZQN3eQnAenR4MqAuy7D5RUzgU87unp6gDFQRQShb9kQW6aXYtjceo6n1kZu/QZSKivu+1pa/wtarPkWG7mqgcTNpQW57QAXSO0nbIuNUqwsiOakgxrVPENLrMUGBxN8JyDagFqC6AHCDacERAvxgGEkuqDnypsifwkC7YbjIfmmiGbSiCl60gIYGlGRAfIkFbDbaypy5BjmBKLEvaJ2+3yGeIsxDJCzSTQUGYoYZWCUllFim83eAMSCqDoeVT/pAAAAAElFTkSuQmCC"))
    val playerHurtChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAACEElEQVRoQ+2YwW3DMAxF5VvXcY8doFNUc3SGzuFO0QF6TNbpLQUd0KBpiqJkMXBd+RIgYaj/9ElJ1hBO8gwn4Qgd5GhOdkf+lSPTNN0ocIzRrQLcEgMAgLzFOLN8TlPoIIY6dnMEy4o6Anq8XHEB4RA4oVBeXjBuIOjEz9cd4+n1/unVKx0k1X+0rNANjAVXvMqrqSO8NyQQLK/WvdIchPcGd86rVzoIn+lcWdE+8SivXY7wsxSWFQjl/cFBKAz+tmezLAbRxKOg53EM3x9XcWF7eR/D5br9DVezWigTiEU8VV0DQv9fA5UEKRUvgcDs0wdcSjmS2pesUCJI6qxkOITOIeAIPlhG0nfWfBinbaYbkL0QniDaarcC0SCGsL5xuYWwevvjpVXriGUcyZkFpAQCReZg+OoE5SWtWJiPQ2jjcJgVCN0HcslzMJJoDSQFkYPBvUcFySXPwVibuXYc+m4zg7RocKvolnG0vBYQqaxaDuqVC13JgvANCQRp0FK8BFGaIxW/gGhlJb1f5+JBdO7wV5OD3pFJx5lBC0gJkoSUvsLW5NC0iiCWmw6e1PIfXmI1OSQYGHsDYhVUI6IFCK6ytGc2IFYIrxWoJC+/V14c+UsQCIwwiyOWlaZkth4Zi4vG7EgHeeTUJ8Y6pyO5nfgAE69KAFdMtyhHBwF9HeRoLnVHuiNOM9BLy2liq9P+AhJ7sDqaq0RRAAAAAElFTkSuQmCC"))
    val playerDireChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAACmElEQVRoQ+2YS07DMBCGnSWLnINKrLJAqjgApyAXKAfgDByAXiCcggMgJBZdIZVzZNFl0LgedzKZ2E4aq0lxNqCkHs83v+eRZOpKruxKOFQCmZuSSZF/pUhVVQ0FLssy2gmIZhgAAKQsS81SVZVKIAHnOJoi+zxvbu9qlX0dvQBF4IqlShQQDoEBjQkTBaRZq+b3J1erulaHjyPGzeNJmRiqRAGhSb5YECy5UK0QAo8WqBLreE2qCIUA5yWQWIk/OQj2DQ5BVYnRVxII71G+YyUpMmVfmUwRWqmk/OAgNFfw2TlleTAIHQTZxnZALIpCfb7uxMHi4aVQu1332bmzWBAIn2LvNxtlxg+9Hhog/MVxZCjIPs+1PWND23QETAxQL4gwglsDzVo7rdfiOIKdHO4hCESfXqCSpAjYM+utP2AXJgPsO77jJ4LwxJVCQKNIIRAE1+AxAjh+j9oFe6u6zqTA4O9czbQDEgIhaktuSk6HgOC0bBQXt+mDaYF4IDh06+2P7joGhKx37oPKcUq7aCAE2nHC8OoEgFLFckB09qH5aUq+ZmiB4HjBaH2VTYSRnPaABO1Dc+l7u7Uvaj4Qn3GvMr58Ms8H7QOqwPX+fPoO0AKB/gAlbykXTfxWJOib3ZJgYMLogOjEMR8M4H/ekOBeTy5pdun3UlCG2uj7PY42GVarp7cSu6vdV5p/XNUt9O1vjA0+lPImqUEkWtcQJzkSCkEc0NWO7u2z4fJVBAmZRLnRkDX8iI2xIcHA3h2QUIfGODEFiMnD1inqgIRCzKGi8e/KVpElQdA8gxyzipikC+2ucxCEVtbjSx2pQAnkkhKhENelyDmfYS6pBk36ReaFFLwEMocjRX1IiiRFIkUgHa1IgR1t9g8sG+s6aH7n+AAAAABJRU5ErkJgggAA"))
    val playerHealChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAACFElEQVRoQ+2YMXbDIAyG7bHHScbuvUV8jp6h53Bu0b1jcpyM7pNj8UAIIWP04rp4TGTxf/wSYPruIE9/EI6ugezNyebIv3JkHMfJBx6GwawCzBIDAIAMl8vMMl6vXQNR1LGZI1hWviOgx8oVExAKgRMK5WUFYwaCTjy+n0O8fTz73qpXGkiq//yyQjcwFlyxKq+qjtDe4ECwvGr3SnUQ2hvUOateaSB0pnNl5feJRXltciQ6Sy3HERBK+4OC+DD435bNcjWIJB4Fnc7n7ufrzi5s75+n7n67Rf/halYKpQLRiPeVlYD475dAJUHWiudAYPb9B1xKOZLcl5ZjTc4pFiR1VlIcQucQcAQfLCPuN20+jJM20whkK4QliLTaBSAiRN+H0NMUfP3R0ip2RDEO54wTtwoCVWZg6OoE5cWtWG4SKIQwDoUJQPB4EdRuKnkGhhMtghSM438SyCC55ApnVA1dOE4EUqPBVYIrB/nlNTvi33ZUHss8HbqSBaG7LChje2mRzMVzNGtzpOIdiFRW3Pd1Ln4GzVzEleRIVQ1OXC8FpARxQtZ+wpbkkLSyIJqbDppU8w4tsZIcHAyMHYFoBZWIqAHCLU4RiBbCfClSDEDvlZ0jfwnCnYaXS3LniGalUUzSS0Jw0ZgdaSAv8SAc9JiO5HbiHUy8KAFcUd2i7B0E9DWQvbnUHGmOGM1AKy2jiS1O+wvROsA65DQ76AAAAABJRU5ErkJgggAA"))

    val playerBaseSpecial = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAACQAAAAECAYAAADmrJ2uAAAAOklEQVQoU2NkQAKGhob/kfnnz59nROZTi43PHriF6IpgllPbUYTsATsIlyJqO4oYexgJKaKWo4i1BwCsKx2XCVZoUgAAAABJRU5ErkJgggAA"))
    val playerBoostSpecial = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAACQAAAAECAYAAADmrJ2uAAAAS0lEQVQoU2NkQAKGhob/z59bAxYxNAphOH/+PCOyPLXY+OyBWwhTBHIICIAcRgtHEbIH7CBkRSghRGVHEWMPI3Lw4YoSaoQUsfYAALVdR5fmYNrmAAAAAElFTkSuQmCC"))

    val playerBaseWeapon = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAYAAAAGCAYAAADgzO9IAAAAH0lEQVQYV2NkwAEYyZdYvHjxf5Du2NhYsClwo4iWAADCcgwHip7LSgAAAABJRU5ErkJgggAA"))
    val playerFiringWeapon = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAYAAAAGCAYAAADgzO9IAAAAN0lEQVQYV32NyQ0AIAzDnEE7XQcFpVIQH3g1zSFrwRLIFyBafmJaJzzNmFf4WTwZnnf3wKtquBuM1BYBeezCIQAAAABJRU5ErkJgggAA"))
    val playerFiringOrangeWeapon = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAYAAAAGCAYAAADgzO9IAAAAN0lEQVQYV2P8NJPhP186AyOIZmBgYICxGUEcmCCIDZOEq4QJwmj8OnDaAdK+ePFisOWxsbFgUwAyYh2lCvTdPAAAAABJRU5ErkJgggAA"))
    val playerFiringYellowWeapon = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAQAAAAPCAYAAADDNm69AAAAHUlEQVQoU2P8/3/CfwYkwAgRKIAKTWAYhgII7wIAJyZCU9+uA9UAAAAASUVORK5CYIIA"))
    val playerFiringGreenWeapon = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAYAAAAGCAYAAADgzO9IAAAANklEQVQYV2Nk+M/wn4GRgRFMgwCUzQjmwATBHIgkQiVMEC6HVwdOOxgYGBYvXgy2PDY2FmwvAIPXFgGVdnQFAAAAAElFTkSuQmCC"))
    val playerFiringCyanWeapon = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAYAAAAGCAYAAADgzO9IAAAANklEQVQYV2Nk+P//PwMjIyOYBgEomxHMgQmCORBJhEqYIEwOvw6cdjAwMCxevBhseWxsLNheAKvRHv7OxL34AAAAAElFTkSuQmCC"))
    val playerFiringMagentaWeapon = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAYAAAAGCAYAAADgzO9IAAAANklEQVQYV32Nyw0AMAhCYVCnY1AbTLCn9iTyyWOjmyB9ASCafmJaJ9xmzBv+Fk+G55IGXlXDPbTOHv6d1ZiQAAAAAElFTkSuQmCC"))

    val playerBaseEngine1 = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAABoAAAAHCAYAAAAWAl2LAAAAQ0lEQVQoU2NkgIHv3//D2TAGJycjhhg+ATxmIAwafhaBggTZV6QGG7YoQDIDMw5AlpFrCbJlaGaQFtkkpQxUxXSzCAD+TB4IwJjyDwAAAABJRU5ErkJgggAA"))
    val playerBaseEngine2 = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAABoAAAAHCAYAAAAWAl2LAAAAQklEQVQoU2NkgIHv3//D2TAGJycjhhg+ATxmIAyim0UglyJbRqpvsIUMkhmYQQOyjFxLkC1DM4O0OCApwlAVDz+LAKzqGAh4HcFtAAAAAElFTkSuQmCC"))

    val playerBrakeEngine = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAABoAAAAHCAYAAAAWAl2LAAAAP0lEQVQoU2NkgIHv3//D2TAGJycjhhg+ATxmoBqErJBUS7A5GMkMTBeDLCPXEmTL0MwgLWhICkdUxaMWkR14AOVbEggz65+VAAAAAElFTkSuQmCC"))

    val playerBoostEngine1 = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAABoAAAAHCAYAAAAWAl2LAAAASElEQVQoU2NkAIHv3/+DaWyAk5MRpxyyBAEzGPFaAjOIkGX4LIGaMRwtolscweIBWzgTihv0VILHDEgcwQxEVogsRshCIswAADTHNbwgviCpAAAAAElFTkSuQmCC"))
    val playerBoostEngine2 = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAABoAAAAHCAYAAAAWAl2LAAAARklEQVQoU2NkAIHv3/+DaWyAk5MRpxyyBAEzGPFaAjOIkGX4LIGaMRwtolscweIBWzgTihv0VILHDNQUhayQVEuwORjJDADFrCnPinVJugAAAABJRU5ErkJgggAA"))

    // BigEnemy:
    val bigEnemyBaseChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAJESURBVGhD7ZgBboMwDEXLbjMu1jPtYt1xOhkwM8Y/tkOC2oonRaUp2P/bSQXcLt6Zx+PxpLF87UZNnq/l0+UMA5pMzpARDjiO35vvPajN5RrRgYlxHIflsDkydsZM0ci+Or/T5xlwrqgZaMTqBNGzG4zOETFjGrFMnNkNRub0zJjVpZMtE9lu6KS112stVpxdR5DjjAiKIUXIaqL4FiinFcNcWrICtUgDhP5eC4qxMZKpFkLH+HkO05D0yLPrSM+qEb3im0urFbITuiutWY147W6xHLJkNG06gtrO82ea4VyeJia8tFBAScZo5NxITia9R5AAr4ISPseLlWHagRkRpVsVfb3e4Pdhqy8Ty0LecaxGIhdm8Yy0gG9ZwNIig7PJI9yH/3s5eVwP1pXeI6/KkNkfeXT1ns3XFu+TqSN1JrRIq+VSuGUiEqMMa3f2iB4SEsZz9FlTbS8GzVljz8E9wkKQCZnUFuDHiBE0QklQomMCZkqxY/E7/WtRhbnKcvB8exwjWoAeFjQvr2PQvITmrcHXYTp15HyAEa8Cpd/lfORYQvPoNwL//ukdQXBF9BoukTmHB6484rM6UnoumEEVylfOpj7+eq+VeYP4qhSeRzxKldLrnUHzEr8DiM/71/L3SRSuqq4umq9Hap6M8D45Zqa0ZCyy529hrax97UgbM+egTRC7irR/9OWqt11O0gSxM0JYL8j6PNP7WCtEmyBMIxrb2D5Y5v0YCUQxlsMV6zxNyIhmFhwTUQIXwxd+cXGR4Xb7A0XmpwEgPmc8AAAAAElFTkSuQmCC"))
    val bigEnemyHurtChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAJYSURBVGhD7ZgBcoNACEWT3qYerL1TD2aP04qzWPzCArpr0oxvZieKK/BhN9HcLv4z4zj+0Cin3dgT5618upwhAMnEDAlhh+/DsDrvwd5YrhB0TAzDcC+HzZG+M2KqQlDE9zjOn2fAsaJiTCFaJ4ie3WAwRkSMKkQTcWY3GBnTE6NWlyZrIrLdwKB778dcND+bjliKM0mQD5mErKblX8OKqflQl5aswF6kAALP92L5WAnJVMsCfXxNZzQkPeJsOtKzakQv/+rSaoXsBHalNYsQr90tlkOWTE6rjlhtZ/uZYjiWlxMTXlqWQ0lGaGRuJCaT3iNWAl4FJTzH85Vh/sHJJFF7VMH7cYN/wM9bxpeGfOJYhERuzOIJaQE/sqhLa4pHCg+H/bz/+ZDHe6nlld4jz8o9sz+yYPWmQLDYjsP7ZO7IHhGYJJ4TMnFNRMSHB+de3SM4yuUZSoxt9Kkl6uH5IJs2yuUVh/YIJ4IJMDKolYDnI0pICAWxAh1NgKj5jvrv8q1FFeYqy8H2Mq0pVSGYAI4ybQXZ5X3FbNolZNcG31emqXTpyCNQhXgVqF2X9sixhOzWNaJ2/bU7YsEVwTVcLqtk5vCoVd7itTpSey8grApZ9iyWH8suWZ61Mv8gPivm+4hHrVK43ovZtEtqfj1e71vL2ydRuKpYXct+BJnzLIT3yRExtSWjkZ2PcK6c+9KRFmLOAkUQm4q0fvWdAswxWi8nKYLYCCG0P8h6vNNH0FYIiiBUIYgmTHOW+X+MErR8lMMFbR4SEoJQsGgSNSwfkcQvLi4y3G6/wBGlAYICre8AAAAASUVORK5CYII="))
    val bigEnemyDireChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAL7SURBVGhD7ZiBcYMwDEWh24RlMkZn6CgdI8uk41BLWFQWki2Bnbve5d05gDGyvmQbk+nNf+b5fK5Q8uUwzvTzkY9NXiFAEunTJYQMLsutuB7B2b6aQqRhYFmWOZ92h9uOiGkKuX0tzOAPHl8B9eUV454jxMhsELIPj5i2kMd2eGU2CN5nS0w1uut9WufHuhuMZkN2evZ5EgGAL5qdQ0bgYRCQLKAIIuIE2OBO8GhSvQerT82GOrRAwDy5+1PhAgB5fRbLRiEkEi0LaeN7nbFwRvRzyMjIqAGj7LdXrQvwTMis9GYX0kp3j+EQJeJTkREr7VT/SjHUV8snwj20LIOciNBWW9ga3ZYlX7UJzxHLgVYEOZ4Mz4/6y1qCjSNO1LYq8nk5wT/n0m9pS74AaWdhcdhxbELwZd61fK9TUbQ2VOgEHUqACLpXK5QEY2jNSSGUa3zOf8ODn2vgTuKeXMvbI9qslth+hefISHBrlLdH0S3SHJkfcWT0INh9oXmCGTknQjqppZw7ronw2KhDvjfmiCwccIzq4Hgm2i0bUKeVIxfnCDliieCd6g60bfhwCoFOrI6uOQCg8Xua3qmU1PotGbRqQYQpyrxQ/QatT1gJb3J1yfXRECIdkEUD6vlzRFmP0U/vjfSLbeDGdk62eaHnbAZlxAftp3ArEnxvSAwhrQjU7qftxj7WeRtxnobR8U8OvGTtJPb97hnBMZ8ifZy4JTg7OvzJQQSFUETkGN6giZsvM2UbHW4Lih15i3Fz5MIKdAYUUvvG2LAiJOrTKgRAZWzIOO0r7HutyD+IHnqsQDVg+EpAw8mhpUdq24bjNGGF4HW8nlPPACwg8LAmZtwc6QwuJPQdn4cwZxfSnideKKoyula9E/h6hAdh6Ob3Dvd5z9H1DywaLtLJaL0PEkFzfM8IVfTLzDikCGA/Ifp/+l6LvEQTARyEACSGM+abvo02QqQIQBUi0YUdjUE7r2Bw0LKRT3e0dhKXEMnmsM+JGnYw2o6/efMmwjT9AlYIQ9i3m8e0AAAAAElFTkSuQmCC"))
    val bigEnemyHealChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAACrUlEQVRoQ+2aa3KDMAyE8XHgYjlTLkaOQ8ckYoTQY2VMpmWaX80klvVpV7aJW4abvMpNOIYUyDzPSwWfpik1LluslnnghCj4N0Eyc0EgW4XGcZhfr7XAV6nSOlcIwgNXgApyFQRZsM45jeP6Fi2cC6JBXKkGB1nnScCYIBLiW2poqiDKqCAWxDfUsFSJYEwQkjUK4C2tfKVrKULGFQeQzGALYrdUC59ngXjje0VVQbgaLb0hJ+fA2VVPi6XF2IFoamRBZIzn5/DwGNZDAbycWk1vxTiASDVaQHgMCXI23gYo9rNLQQiCJueqoJuqZVNprw3EslXWDnzisyCZnHYgmq24lMiK0wvEg9DsBYMg3rYaXbNWVBRv5esCYiWgVdCyVmRXRA1Z2FURdCBPQNsMpTU9kGwsbT5+Mt5AvP7wjiHeZxFIa1xtg9VBSnn3zrK8d7HWVynlubx3wkcZSo94Mi9ahu8DkumPtDCkLA08q7CSAPVJQZY5FaAmyROT72mQZ1M0RlDBCqODyEpaFaVELIg6Luo3LwaYx3kQStSyDGqtSE2piJgPB2n1NgoSNV8Q5zoQy06RzSyg0yCkBOjVNQ9uE+Rvnnw0j1GIWJE/DyIl7uX1qBfkkg3uP7YitwexrOatakiDW4ojYz8/aGA7uwyYsRqSTC+Q6qTUMR5JDjmiWBZO7FvbWavGSp+3fhlI/UWm/4OVtRcExwx0QZPf859HWqPyQ2IUI2EfL9QBJN0nUaItPYLG/Hzv8MxOfXIKJnu+yvSZAiiv5KAfsaFCfRFEu1eE7kcgkOjk2rEnVueIu3736o3nltpnTpHvB1P1d7ko/7AQXk/z/omCZfYj68JHXtdp1dfqBIEc1u56D65URUvCE8eKgV458NhNIB2d0y3UP0i3UnYKdBtFfgDQ5+3eh4PB5wAAAABJRU5ErkJgggAA"))

    val bigEnemyBaseSpecial = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAADkSURBVGhD7dYLDsIwCAbgzut5Xs83w1JMQ4DS16L2/xIzsw1KGYsmAAAAAAAAAID7Pc/z+owqc8zKZ+R55OPPO/JxDdm917F2vS6ySGt0nHEI5wjQO2QllB2l++gcHzWcy7tu5eBYSck19o5YBbCyEK8oL0dQvQDSu8iNedZshONlnHW+ZngjHCATMa0gureMq30v1dbh60rsZr8jslNaN2fqWG/TJyJnlXkdc+b6w8oXic3+5on0oU7JLlpW3Svg368p2tHA3LfY/B1pMTD3LeY9kdaCJ28QoxU2ufMAAPBFUnoDCzSkhcwu4kUAAAAASUVORK5CYII="))
    val bigEnemyBoostSpecial = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAADlSURBVGhD7daLDsIgDAVQ8P//eY6Em2BDS3ktKvdEg260QGHRQERERERERET0uOu6X/c7fx1W5liVT8vzyu3Pi7ndQlYvxr3jDZGT1LbcOg7eHB7VCmkJZUVTv3QNbb78Abms+1oOxEq1XFPPiDYBKCdiTcrK4dWcQDI6yJN5tiwE8TJOu94yvRAEyERQS5j6lnGtz6XWOLhfiz3rd0RWqlaRlUbGO3NH5FkFq2LWuQYtnycW/mZHhqRKySpqdvWV+O9X462o59z3OPsZ6TFz7nss25HeCa9eII+W1+rKExHRFwnhDXGrts0QMCjRAAAAAElFTkSuQmCC"))

    val bigEnemyBaseWeapon = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAA1SURBVGhD7czBCQAgDAPA4P7zdR1F6Ab6ELmDEPJJ+EFVzZ2e15z8jm4AAAAAAAAAAIAnJQtE7Q4HzpK9GwAAAABJRU5ErkJggg=="))
    val bigEnemyFiringWeapon = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAABdSURBVGhD7c9BDoAgDARA9P/v4zsKKokajKYXOcz00FBIsySillKjyDnXOHv1vM3vvSnnbXfAfPSYqVQnzGV+7nVetfveu189hfkacohPNEOFAQAAAAAAAACAqJRW2JYy46C8Gi0AAAAASUVORK5CYII="))

    val bigEnemyBaseEngine1 = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAABSSURBVGhD7dIxCoBADATAxP//OSKcYmFzRxqPmWZTLQQ2AAAAAAAAAACgSY6cVhU1zkfmXF9Hx+0Y+XvbPLI8rct7GquT6Oho8bXzWR0dm4g4AUNGEgEwBjnxAAAAAElFTkSuQmCC"))
    val bigEnemyBaseEngine2 = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAABNSURBVGhD7dMxCsAwCAVQ7f3vbAmkpWuCQ1veW3T6IGoAAAAAAAAAAECTnHVZVdRsb5lreR0Zl2PWzzPI22z/yPC88d3b7sgYfrKRiBOHIwwLR7XcgAAAAABJRU5ErkJggg=="))

    val bigEnemyBrakeEngine = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAABFSURBVGhD7c8xDsAwCANA6P//TBWJVh26JGK8W8xkmQAAAAAAAAAAgCHZua0qqs9X5l7fRMfj+JHlO+R0wETHcnUCfyJuni4MBSxhTy0AAAAASUVORK5CYII="))

    val bigEnemyBoostEngine1 = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAABXSURBVGhD7dMxDoAgDAXQ4v3vjDEB42AMCIPE95Z2+gltCQAAAAAAAAAAmCSV2i3nyKU9pdSXNyOj2kpd3qvX302yap3ojIyrf2/k8LU/MuzpRFqNZ0TsulYYAlXcQt0AAAAASUVORK5CYII="))
    val bigEnemyBoostEngine2 = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAABaSURBVGhD7dMxDoAgDAVQ8P53xpiAMmgU0kHje0s7/bQlJAAAAAAAAAAACJJrHVZKKrXd5TyWF5HRLLV+3tT2Z5dsnl40IqP37xfZvO2PTOkHuOrvRGQcUloBbekj9jRj8cMAAAAASUVORK5CYII="))

    // small Enemy:
    val enemyBaseChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAABkAAAAZCAYAAADE6YVjAAAAAXNSR0IArs4c6QAAAVlpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDUuNC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6dGlmZj0iaHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8iPgogICAgICAgICA8dGlmZjpPcmllbnRhdGlvbj4xPC90aWZmOk9yaWVudGF0aW9uPgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KTMInWQAAAU9JREFUSA3dVctuwzAMc4p+ZnPYd+yw076jh/QzB6SgAxqsTDlJe5uBWC9KlK00LcWsZVlWPCaUuk7lAHwt6ykCMiNvtzkSfELiiC7sIsrdjkLCCN9IAPqabyH1PRN1lLSRaLn78ijzPE/q29OBR55bV+fMfNrd2Sbq0TAwNzQSgoCYozg2Za+LhSkBjvOK906skxdXwAHp+16Pj4qNdCdhgEVVkoBSY9CzhjsSgEdEiLuVEQCbvl0bUamfF+huHcHUPHTAtwYO6pn8WUvDZxits9WvVNuGpL8ytcnClnBVf6fOVf2jPDsTZDmCWi3ZdvHuyrQWCmRPxMEmFnVhdyeJHUUbSbpiPNqKffm0EEgJIHTaqjNGyRhPAX93EjixWFB1DpfSYWryaNPZsCNKJWNxxqLUU1i+EVEs5mxH0H4Tykig/mfQpzgXV59i/4f+BO74Z/Zva8DpAAAAAElFTkSuQmCC"))
    val enemyHurtChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAABkAAAAZCAYAAADE6YVjAAAAAXNSR0IArs4c6QAAAVlpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDUuNC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6dGlmZj0iaHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8iPgogICAgICAgICA8dGlmZjpPcmllbnRhdGlvbj4xPC90aWZmOk9yaWVudGF0aW9uPgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KTMInWQAAAU1JREFUSA3dlVFywyAMRN1Mj1nO0tyF3jP1kj5mIwS2278y40hIq13J4Mm2JavW+tCTpKahSzUC7+yXBFBW3WFzCPxFJBO60UW0hx2FghW+iwj0UUoo/d1WPC7aRZzuq9atlPLmsSNfeNVl6z0LzmLe3dUm2mg6sOzQEJQAmLM4mkpfF8RYgeN5xfcONrO3jCADErvvY5xdNDJMQiIjQgAbMbOGBxEVroQiMfuZgPLT2yWh/Ua0l9N82MyewTS4OuDWKIA/s5+P5y2c5RV3HvG/TCLA/gX2j5CC1s3Pz71nPdqKeibWpWdCJ680610kHtDZK3OQCGZPxGkPVrzaD5MIoAQr7oljYz7uwTXr0wDECiCfvfvksOSYQvFhEgW1IHSfS4HNMK149ROnoSvIMusYfJ8i1VsJQbKymUC/264I0P8ziDkuy3vMsf/D/wawWmFOvGwlPwAAAABJRU5ErkJggg=="))
    val enemyDireChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAABkAAAAZCAYAAADE6YVjAAAAAXNSR0IArs4c6QAAAVlpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDUuNC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6dGlmZj0iaHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8iPgogICAgICAgICA8dGlmZjpPcmllbnRhdGlvbj4xPC90aWZmOk9yaWVudGF0aW9uPgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KTMInWQAAAbRJREFUSA3dVTtOxDAUfEZLGa7BSnCAPcymoOYIFNBwBGqK7F3IAUCCa2xKkEwm0VgT5zlhoSOS5efnmTfv49WaOV/TNBHLuSq6TuIAvLF4kgCVwVtNjgJ/EfGEzphFvq9mlBGW8EkEoJt6n1F/d0QcFU0iGu65OVhd10F9azbw4HmfK+IB4Yu7frL90ixL2JkfJAzMG1oOfq+qRZwX60eVIHuKXV51g5n3nffursqlSpA9FsTuo61WzbYy3qwSL0Nkf90d7eFlzPMuLr+J0Fr4eKtSUTMR3ORCh9smEUqGthQYthW2K4ILCPHbP9U0JzuTQcuROYXQWq1k+NHwZSECbezsLV8UfDoTxcLm3BQ/zlxyA/DLQmr4eRvja3Uxlt6OwMeQHpowzcDb4uH1uI0dJ6BiuyCIKBh4L5YCfu6CQRhLP+LVN7H1GeMiJ+DMhVaQzBnwTB6xiIu7WSUE5kSe9dVguGt48oZdqyGROwCweeaAGYB+YrCzCmBmleREnBmEj2LbdQFzol8x5Bf3vBrNTAMxuN6rrVW4YktCGqhkewLpN6GKBOofF32K8+7Vp9j/YX8DGgCc0Wge/acAAAAASUVORK5CYII="))
    val enemyHealChassis = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAABkAAAAZCAYAAADE6YVjAAABNElEQVRIS72WURLDIAhE8Z7NWTo9i7mnHWzJbHBB7Uf9jMhjcaMWIaPW2vTzcRyFze+uGZIo4Hg8REpZBlzQ1lo9z6G4W6ILoKt+hOhSDwohrKKsdVhgCLmpINXM9iZbfynJKpkBbD7KsQUx10XOSyErKlbbyXJ1JTOIB1ztIXalkJUKMeYpRV7S/9XBqr5giymsSm9Bi1GADQVFcWgUjaEQX+UKJGtpCDFQd5IeMyLilbAYb/dRiR4lrX0aHgzckzAI8vB24Zk1Ad4gwbocsgMwmoFgbYcMtjOpq4p8nGuV3kkjJKkq3QOddOrN4vTs6sm8IkuA5sD2ONPgPxRDUBEC7DuDglQKYUfC4B5NjAoDc6Q34xSU/UDfuekdbznYawXvEovD10z2wtl/kSyo8SF/gbwBLEpggZTzQx8AAAAASUVORK5CYIIA"))

    val enemyBaseSpecial = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAABMAAAAICAYAAAAbQcSUAAAAAXNSR0IArs4c6QAAAVlpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDUuNC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6dGlmZj0iaHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8iPgogICAgICAgICA8dGlmZjpPcmllbnRhdGlvbj4xPC90aWZmOk9yaWVudGF0aW9uPgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KTMInWQAAAGFJREFUKBWtjVsOgCAMBIvhdp7X86HVtKwTCZrID+0+pmZra/bHOzjl5ChwK5f25gB6y63zBeRF5GvC3MCl9Dgwp3tmXXw0MtGHaTZADIbuKPVU72cwaSjK1FCZrwHgP2juKXxAc5e6i3EAAAAASUVORK5CYII="))
    val enemyBoostSpecial = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAABMAAAAICAYAAAAbQcSUAAAAAXNSR0IArs4c6QAAAVlpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDUuNC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6dGlmZj0iaHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8iPgogICAgICAgICA8dGlmZjpPcmllbnRhdGlvbj4xPC90aWZmOk9yaWVudGF0aW9uPgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KTMInWQAAAF1JREFUKBWtjdsKwCAMQ1vZ//9yR4RoCBMn6EttLqdRFRUXHjgJjgIzo2t/+N5rWjoBoef5hzAYfomeT8/pPrIQP42RmJ9tliAPUgdKPdXnGftpiGXXrLJfCfC5ar7FXU3PegQQ8wAAAABJRU5ErkJggg=="))

    val enemyBaseWeapon = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAA0AAAACCAYAAACDvVapAAAAAXNSR0IArs4c6QAAAVlpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDUuNC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6dGlmZj0iaHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8iPgogICAgICAgICA8dGlmZjpPcmllbnRhdGlvbj4xPC90aWZmOk9yaWVudGF0aW9uPgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KTMInWQAAABdJREFUCB1jWLx48X8GEgBIPRMJ6uFKAc3nBdM4I+s5AAAAAElFTkSuQmCC"))
    val enemyFiringWeapon = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAA0AAAACCAYAAACDvVapAAAAAXNSR0IArs4c6QAAAVlpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDUuNC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6dGlmZj0iaHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8iPgogICAgICAgICA8dGlmZjpPcmllbnRhdGlvbj4xPC90aWZmOk9yaWVudGF0aW9uPgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KTMInWQAAACNJREFUCB1jWLx48X8GEAQBImiQeiawYpgGRgZGsEZcNFQxAIJKG8WoJD+LAAAAAElFTkSuQmCC"))

    val enemyBaseEngine = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAsAAAADCAYAAABF//VLAAAAAXNSR0IArs4c6QAAAVlpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDUuNC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6dGlmZj0iaHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8iPgogICAgICAgICA8dGlmZjpPcmllbnRhdGlvbj4xPC90aWZmOk9yaWVudGF0aW9uPgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KTMInWQAAACRJREFUCB1j/P+f4T8DFDAyMjDC2DAaWZ4JJkg0jawbmyaYPABEFQv7LTkNmAAAAABJRU5ErkJggg=="))
    val enemyBoostEngine = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAAsAAAADCAYAAABF//VLAAAAAXNSR0IArs4c6QAAAVlpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDUuNC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6dGlmZj0iaHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8iPgogICAgICAgICA8dGlmZjpPcmllbnRhdGlvbj4xPC90aWZmOk9yaWVudGF0aW9uPgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KTMInWQAAACZJREFUCB1j/P+f4T8DFDAyMjDC2DAaWZ4JJkgMDVaMzURkzTB5AL1wBgl6SSFdAAAAAElFTkSuQmCC"))

    // Pickups:
    val healthPickup = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAYAAAA71pVKAAAAnUlEQVQ4T7WTUQ6AIAxD5Z5wOrgnpjOdtRITTfRLiq9lY5bNnt77dI3r1lrRvVwoVGu98WOM1GgSMMEV5C40gUHCBMshbXOep3cNBgFrKj4iAgsYrDSmB6yp2q0wkHPTEBIMHmGv9z8YSVqj36k28XbsTzAgvWNP11rZrAjikPiA0MBBwpch8fQsQYZFUxPWEV2ZKIT3y2xrV9/8VTv+4olojdad8wAAAABJRU5ErkJgggAA"))
    val energyPickup = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAYAAAA71pVKAAAAlUlEQVQ4T6WT4RGAIAhGcYT20xGaCvZrBLtP7/PQ7Mryn8R7BFGQ4ahqHmO8p5SCf9YuHooxXngzazFKCkxwBo0WSiBo8BuQIggK7KuG6uqOqko8Uo3tdRysXmBW9XDOInB52DYV5kIwhQHiLMMEf8Fsf+m12Sun9ggjEYNYnjaX5NN3/r1hXsAW7tYS8W63feLKX3UCwOiNaChmHVsAAAAASUVORK5CYIIA"))
    val pointsPickup = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAYAAAA71pVKAAAAkElEQVQ4T62T3Q3AIAiEyyRdTKfTxToJDTRnTqxN/3xD/e4AUZawSika9xDnnIXPWsBQSmnga61tDyIOAwQk0hk4pHokBBETaDCDuo2Jy9oLOMyu5sigAyQEAbg7bK4M2qW4IMICU/gsdRO8Bb92jiDX/q+zOcWmtfquuo0hefXOnyeMBVBCbNp0tvnik1+1A8b+nGjDbTFLAAAAAElFTkSuQmCC"))
    val weaponUpgradePickup = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAYAAAA71pVKAAABEklEQVQ4T52TKxLCQAyGs6J3QCBQnAGDRHY6g0B1xfYUcANO0RVbhWCmU4nAcgYUAgEnQJZJlmTCAjNAVLa7Xx5/UgOJhRD69BufrbVG38lBQ7acQb8e0LtsGZ/7JgjHQQhmECG0FNybWMy0v0JoduRjAIG/ATlwMwwR1lkxY3V0EGpP0XVGXRHCaASnPW7aLXRt96JbXuSwKOaiAcHl2Yo4tnKAj9h0EIaxolNoYmaEWVWEvffgnCO+HscW+J5bEdiVMTOD6COswRsAHBLVKTPCGLF2lWRNQW4jgwu5ODJRe2RLmqOrVm9LnfQGNEhq85L8NWe9YVr1T3PmGcuG6QAk1kMDXknukft+2m29Db/8VXftQb1oRcx+5QAAAABJRU5ErkJgggAA"))
    val fullHealthPickup = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAYAAAA71pVKAAAApklEQVQ4T7WT0Q2EMAxD6Tr8ss51urIOv7cOyEEOrqlOOiT4oqHPblJTJntaa7vXuK61Fv2WC4U+S73x69ayRpGACY4gV6EIBBImWOZz+/69MK9BIGB1xSY2DFUIjGp0D1hddVohIOemIEoQ+Al7v+/BcNIe/U51iLdjP4IB6R27u/bKYYURQ+IBoYCDhLuQuHu2IGFR14Q1oiMRhfDeZVun+s9fdQB8x31o+Db6YQAAAABJRU5ErkJgggAA"))
    val healthUpgradePickup = decode(PNG("iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAYAAAA71pVKAAABAklEQVQ4T5WTvQ0CMQyFHQl2oKBgkSvZ4lKwBUwAW1DktqCgYBEKCtgBKeg594xj0eDqkvjzz7MvSbBSSo13POeck3+zg4fyuJV6Wqnfct/cz1MxjkEUJggIFsFrasUM9SVluug3AhhMUFLLuJiLBzjIXGB9auBpXRrcZU0rYcNwv0kD/V09isIwhdljOog56iNacArhzIqggcLjI7dIAY6qE0Yr9zJ9Yaj6Tn2mXzBagRm8G1tmivNr0NTAq65lA7ZxOIHiQqiA9aXXGJmpvcmjPWBcUagIUlBdkrggFM9GNmfs5uw3jKpzJSGgz0iw2zAfAN+dBq5HatDtthfmn7/qAxRFpWhbJK5PAAAAAElFTkSuQmCC"))

    private class PNG(data: String): Base64Encoding("data:image/png;base64,", data)
}