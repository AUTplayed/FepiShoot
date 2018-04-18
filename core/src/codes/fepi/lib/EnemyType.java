package codes.fepi.lib;

public enum EnemyType {
    Basic(new EnemyProps(32, 32, "enemy1.png")),
    Movement(new EnemyProps(32, 32, "enemy2.png")),
    Advanced(new EnemyProps(32, 32, "enemy3.png")),
    TrueShot(new EnemyProps(32, 32, "enemy7.png")),
    DoubleShot(new EnemyProps(32,32,"enemy5.png")),
    DoubleShotMove(new EnemyProps(32,32,"enemy6.png"));

    private EnemyProps props;

    public EnemyProps getProps() {
        return props;
    }

    EnemyType(EnemyProps props) {
        this.props = props;
    }
}
