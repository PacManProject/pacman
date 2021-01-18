package src.models;

public class Ghost extends Thread {
    public enum directions {
        Up,
        Down,
        Left,
        Right
    }

    directions direction = directions.Left;
    directions directionNew = direction;
    directions directionNext = direction;

    World world1;

    public Ghost(World w) {
        world1 = w;
        this.setLocation();
    }

    public void setLocation() {
        while (true) {
            int i = (int)(Math.random()*world1.getXyWorld().length), j = (int)(Math.random()*world1.getXyWorld()[0].length);
            if (world1.getXyWorld()[i][j]) {
                world1.xg1 = j;
                world1.yg1 = i;
                break;
            }
        }
    }

    public void run() {
        setPriority(1);
        while (true) {
            try {
                sleep(450);     //Geschwindigkeit von PacMan
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int i = (int)(Math.random()*4);
            switch (i) {
                case 0 -> directionNew = directions.Up;
                case 1 -> directionNew = directions.Down;
                case 2 -> directionNew = directions.Left;
                case 3 -> directionNew = directions.Right;
                default -> System.out.println("leandro suk cok");
            }

            this.move();
        }
    }

    public void move() {
        switch (directionNew) {
            case Up:
                if (world1.yg1 - 1 >= 0 && world1.xyWorld[world1.yg1 - 1 % world1.xyWorld.length][world1.xg1]) {
                    world1.yg1--;
                    direction = directionNew;
                } else {
                    impossibleDirection();//falls Pacman nicht in die Richtung gehen kann
                }

                break;
            case Down:
                if (world1.yg1 + 1 < world1.xyWorld.length && world1.xyWorld[world1.yg1 + 1 % world1.xyWorld.length][world1.xg1]) {
                    world1.yg1++;
                    direction = directionNew;
                } else {
                    impossibleDirection();
                }
                break;
            case Left:
                if (world1.xg1 - 1 >= 0 && world1.xyWorld[world1.yg1][world1.xg1 - 1 % world1.xyWorld[0].length]) {
                    world1.xg1--;
                    direction = directionNew;
                } else {
                    impossibleDirection();
                }
                break;
            case Right:
                if (world1.xg1 + 1 < world1.xyWorld[0].length && world1.xyWorld[world1.yg1][world1.xg1 + 1 % world1.xyWorld[0].length]) {
                    world1.xg1++;
                    direction = directionNew;
                } else {
                    impossibleDirection();
                }
                break;
            default:
                break;
        }
    }

    public void impossibleDirection() {
        directionNext = directionNew;
        directionNew = direction;//Richtung wird zurückgesetzt
        switch (direction) {// erneute Abfrage mit der ursprünglichen Richtung
            case Up:
                if (world1.yg1 - 1 >= 0 && world1.xyWorld[world1.yg1 - 1 % world1.xyWorld.length][world1.xg1]) {
                    move();
                }
                break;
            case Down:
                if (world1.yg1 + 1 < world1.xyWorld.length && world1.xyWorld[world1.yg1 + 1 % world1.xyWorld.length][world1.xg1]) {
                    move();
                }
                break;
            case Left:
                if (world1.xg1 - 1 >= 0 && world1.xyWorld[world1.yg1][world1.xg1 - 1 % world1.xyWorld[0].length]) {
                    move();
                }
                break;
            case Right:
                if (world1.xg1 + 1 < world1.xyWorld[0].length && world1.xyWorld[world1.yg1][world1.xg1 + 1 % world1.xyWorld[0].length]) {
                    move();
                }
                break;
        }
        directionNew = directionNext;
    }

    //UP: Y wird kleiner
    public void moveUp() {
        directionNew = directions.Up;
    }

    //DOWN: Y wird größer
    public void moveDown() {
        directionNew = directions.Down;
    }

    //LEFT: X wird kleiner
    public void moveLeft() {
        directionNew = directions.Left;
    }

    //RIGHT: X wird größer
    public void moveRight() {
        directionNew = directions.Right;
    }
}