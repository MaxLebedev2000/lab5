package Humans;

public interface IPoliceman extends IHuman {
    Card setCard(Human h);
    boolean askBribe(double bribe, double money);}
