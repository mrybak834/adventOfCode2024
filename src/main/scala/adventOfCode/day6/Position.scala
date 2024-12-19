package adventOfCode.day6

case class Position(row: Int, col: Int):
    def move(direction: DIRECTION): Position =
        Position(this.row + direction.position.row, this.col + direction.position.col)

enum DIRECTION(val position: Position):
    case RIGHT extends DIRECTION(Position(0, 1))
    case LEFT extends DIRECTION(Position(0, -1))
    case DOWN extends DIRECTION(Position(1, 0))
    case UP extends DIRECTION(Position(-1, 0))
    case STILL extends DIRECTION(Position(0, 0))
    
    
    