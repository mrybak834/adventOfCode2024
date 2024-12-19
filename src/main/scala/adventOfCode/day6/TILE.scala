package adventOfCode.day6

import adventOfCode.day6.Tile.*


trait Tile:
    def symbol: Char

trait GuardTile extends Tile:
    def direction: DIRECTION = this match
        case Down => DIRECTION.DOWN
        case Left => DIRECTION.LEFT
        case Right => DIRECTION.RIGHT
        case Up => DIRECTION.UP
        case _ => DIRECTION.STILL

    def turn: GuardTile = this match
        case Down => Left
        case Left => Up
        case Right => Down
        case Up => Right

case object Empty extends Tile:
    val symbol: Char = '.'

case object Obstacle extends Tile:
    val symbol: Char = '#'

case object Visited extends Tile:
    val symbol: Char = 'X'

case object Exit extends Tile:
    val symbol: Char = '!'

case object Up extends GuardTile:
    val symbol: Char = '^'

case object Down extends GuardTile:
    val symbol: Char = 'v'

case object Left extends GuardTile:
    val symbol: Char = '<'

case object Right extends GuardTile:
    val symbol: Char = '>'

object Tile:
    private val tiles = Set[Tile](
        Empty, Obstacle, Visited, Exit,
        Up, Down, Left, Right
    )

    private val tilesBySymbol: Map[Char, Tile] = tiles.map(tile => tile.symbol -> tile).toMap

    def apply(symbol: Char): Tile =
        tilesBySymbol.getOrElse(symbol, throw new IllegalArgumentException(s"Unknown tile: $symbol"))


//enum TILE(val symbol: Char):
//    case EMPTY extends TILE('.')
//    case OBSTACLE extends TILE('#')
//    case VISITED extends TILE('X')
//    case EXIT extends TILE('!')
//    case UP extends TILE('^')
//    case DOWN extends TILE('v')
//    case LEFT extends TILE('<')
//    case RIGHT extends TILE('>')
//
//    def isGuard: Boolean = guard.contains(this)
//    def exited: Boolean = this == EXIT
//
//object TILE:
//    def apply(symbol: Char): TILE =
//        TILE.values.map(tile => tile.symbol -> tile).toMap.getOrElse(symbol, throw IllegalArgumentException(s"Unknown tile: $symbol"))
//
//    val guard: Set[TILE] = Set(UP, DOWN, LEFT, RIGHT)
//
//
//extension (tile: TILE)
//    def direction: DIRECTION = tile match
//        case UP => DIRECTION.UP
//        case DOWN => DIRECTION.DOWN
//        case LEFT => DIRECTION.LEFT
//        case RIGHT => DIRECTION.RIGHT
//        case _ => DIRECTION.STILL
//
