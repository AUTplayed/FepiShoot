package codes.fepi.enemy;

import codes.fepi.component.FunctionMoveComponent;
import codes.fepi.component.field.CollisionComponent;
import codes.fepi.lib.EnemyType;
import codes.fepi.lib.MoveFunction;
import codes.fepi.lib.Pos;

public class FunctionMoveEnemy extends Enemy {

    public FunctionMoveEnemy(int health, EnemyType type, MoveFunction function) {
        super(health, type);
        this.add(new CollisionComponent(1));
        this.add(new FunctionMoveComponent(function));
    }

    public FunctionMoveEnemy(int health, EnemyType type, MoveFunction function, float offset) {
        super(health, type);
        this.add(new CollisionComponent(1));
        this.add(new FunctionMoveComponent(function, offset));
    }
}

