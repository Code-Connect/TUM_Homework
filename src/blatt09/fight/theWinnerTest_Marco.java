package blatt09.fight;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class theWinnerTest_Marco {
    private Position positionT;
    private char expected;

    @Before
    public void setUp(){
        positionT = new Position();
    }

    @Test
    public void EmptyBoardExpectsN() throws Exception {
        positionT.setMyAnimals(CreateAnimal(new Rabbit(false), "a1", false));
        expected = 'N';
        assertMoves(expected);
    }

    @Test
    public void TwoMaleVegetarianExpectsM() throws Exception {
        positionT.setMyAnimals(CreateAnimal(new Horse(false), "a1"), CreateAnimal(new Rabbit(false), "c3"));
        expected = 'M';
        assertMoves(expected);
    }

    @Test
    public void MalePredExpectsM() throws Exception {
        positionT.setMyAnimals(CreateAnimal(new Leopard(false), "a1"));
        expected = 'M';
        assertMoves(expected);
    }

    @Test
    public void TwoMaleVegetarianOneFemalPredExpectsX() throws Exception {
        positionT.setMyAnimals(CreateAnimal(new Leopard(true), "a1"), CreateAnimal(new Rabbit(false), "c3"),
                CreateAnimal(new Rabbit(false), "a2"));
        expected = 'X';
        assertMoves(expected);
    }

    @Test
    public void MaleLeopardFemalePenguinExpectsW() throws Exception {
        positionT.setMyAnimals(CreateAnimal(new Leopard(false), "a1"), CreateAnimal(new Penguin(true), "c3"));
        expected = 'W';
        assertMoves(expected);
    }

    @Test
    public void MaleLeopardFemaleLeopardExpectsN() throws Exception {
        positionT.setMyAnimals(CreateAnimal(new Leopard(false), "a1"), CreateAnimal(new Leopard(true), "c3"));
        expected = 'N';
        assertMoves(expected);
    }

    @Test
    public void MaleLeopardSnakeFemalePenguinExpectsW() throws Exception {
        positionT.setMyAnimals(CreateAnimal(new Leopard(false), "a1"), CreateAnimal(new Penguin(true), "c3"),
                CreateAnimal(new Snake(false), "a2"));
        expected = 'W';
        assertMoves(expected);
    }

    @Test
    public void TwoMaleVegTwoFemaleVegExpectsN() throws Exception {
        positionT.setMyAnimals(CreateAnimal(new Horse(false), "a1"), CreateAnimal(new Rabbit(false), "c3"),
                CreateAnimal(new Horse(true), "e4"), CreateAnimal(new Rabbit(true), "e5"));
        expected = 'N';
        assertMoves(expected);
    }

    @Test
    public void TwoMaleVegOneFemaleVegExpectsM() throws Exception {
        positionT.setMyAnimals(CreateAnimal(new Horse(false), "a1"), CreateAnimal(new Rabbit(false), "c3"),
                CreateAnimal(new Rabbit(true), "e5"));
        expected = 'M';
        assertMoves(expected);
    }

    @Test
    public void TwoMaleVegTwoFemaleVegOOneFemalePredExpectsX() throws Exception {
        positionT.setMyAnimals(CreateAnimal(new Horse(false), "a1"), CreateAnimal(new Rabbit(false), "c3"),
                CreateAnimal(new Horse(true), "e4"), CreateAnimal(new Rabbit(true), "e5"),
                CreateAnimal(new Leopard(true), "e5"));
        expected = 'X';
        assertMoves(expected);
    }

    public Animal CreateAnimal(Animal toCreate, String sq) {
        if (toCreate instanceof Leopard) ((Predator) toCreate).withoutFood = 5;
        if (toCreate instanceof Penguin) ((Predator) toCreate).withoutFood = 12;
        if (toCreate instanceof Snake) ((Predator) toCreate).withoutFood = 9;
        toCreate.square = sq;
        toCreate.position = positionT;
        toCreate.alive = true;
        return toCreate;
    }

    public Animal CreateAnimal(Animal toCreate, String sq, boolean aliv) {
        if (toCreate instanceof Leopard) ((Predator) toCreate).withoutFood = 5;
        if (toCreate instanceof Penguin) ((Predator) toCreate).withoutFood = 12;
        if (toCreate instanceof Snake) ((Predator) toCreate).withoutFood = 9;
        toCreate.square = sq;
        toCreate.position = positionT;
        toCreate.alive = aliv;
        return toCreate;
    }

    private void assertMoves(char expected) {
        Assert.assertEquals(expected, positionT.theWinner());
    }
}
