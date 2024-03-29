package pl.company;

import java.util.Scanner;

public class Bohater extends Istotaa {
    int LvUpPoint = 200;
    int currentLV = 1;
    int modyfikatorAtak = 0;
    int modyfikatorMieczKryt = 0;
    int modyfikatorTarczakryt = 0;
    int modyfikatorTarczaSzansa = 0;
    String ranga = "Pachołek";
    Scanner scan = new Scanner(System.in);
    int ilośćMikstur = 3;

    public Bohater(int hp, int dmgDOWN, int dmgUP, int exp, String Name) {
        super(hp, dmgDOWN, dmgUP, exp, Name);
    }

    //funkcja nazywająca bohatera
    public void Nazywacz(String imię) {
        this.setName(imię);
    }

    //funkcja sprawdzająca czy bohater kwalifikuje się do awansu na wyższy poziom
    public void LVupCheck() {
        if (getExp() >= getLvUpPoint()) {
            setCurrentLV(getCurrentLV() + 1);
            setExp(getExp() - getLvUpPoint());
            setLvUpPoint(getLvUpPoint() * 2 - getLvUpPoint() / 15 * 10);
            System.out.println(getName() + " awansował na " + getCurrentLV() + " poziom");
            LVupGains();
        }

    }

    //Funkcja zwiększająca lv i statystyki
    public void LVupGains() {
        setHP(getHP() + 10);
        setCurrentHP(getHP());
        if (getCurrentLV() % 2 == 0)
            setDmgD(getDmgD() + 1);
        else
            setDmgU(getDmgU() + 1);

        if (getCurrentLV() == 5)
            Rang5lv();

        if (getCurrentLV() == 10)
            Rang10lv();

        if (getCurrentLV() == 15)
            Rang15lv();


        System.out.println();
    }

    //Funkcja dodająca rangę na 5 lv
    public void Rang5lv (){
        boolean progresRanga = false;
        while (!progresRanga) {
            System.out.println("Twoja ranga wzrasta teraz jesteś Wojownikiem");
            setRanga("Wojownik");
            System.out.println("Wybierz specjalizację:");
            System.out.println("Wpisz \"t\" Tarczownik - większa szansa na udane parowanie, parowanie może zadać obrażenia krytyczne");
            System.out.println("Wpisz \"m\" Miecznik - +2 obrażenia minimalne i max, większa szansa na zadanie obrażenia krytycznego, jego atak zawsze trafia");

            switch (scan.next()) {
                case "m": {
                    modyfikatorAtak += 2;
                    modyfikatorMieczKryt += 15;
                    setRanga("Wojownik - Miecznik ");
                    progresRanga = true;
                }
                break;
                case "t": {
                    modyfikatorTarczaSzansa += 10;
                    modyfikatorTarczakryt += 25;
                    setRanga("Wojownik - Tarczownik");
                    progresRanga = true;
                }
                break;
                default:
                    System.out.println("Błędna komenda");
            }
        }


    }

    //Funkcja dodająca rangę na 10 lv
    public void Rang10lv (){
        boolean progresRanga = false;
        while (!progresRanga) {
            System.out.println("Twoja ranga wzrasta teraz jesteś Mistrzem");
            setRanga("Mistrz");
            System.out.println("Wybierz specjalizację:");
            System.out.println("Wpisz \"h\" Huskarl - większa szansa na udane parowanie, parowanie może zadać obrażenia krytyczne (większa szansa)");
            System.out.println("Wpisz \"s\" Szarmierz - +4 obrażenia minimalne i max, większa szansa na zadanie obrażenia krytycznego, atak zawsze trafia");

            switch (scan.next()) {
                case "s": {
                    modyfikatorAtak += 4;
                    modyfikatorMieczKryt += 20;
                    setRanga("Mistrz - Szermierz ");
                    progresRanga = true;
                }
                break;
                case "h": {
                    if (modyfikatorTarczaSzansa!=0) {
                        modyfikatorTarczaSzansa += 5;
                        modyfikatorTarczakryt += 5;
                        progresRanga = true;
                    }
                                else {
                        modyfikatorTarczaSzansa +=10;
                        modyfikatorTarczakryt += 25;
                        setRanga("Mistrz - Huskarl");
                        progresRanga = true;
                    }
                }
                break;
                default:
                    System.out.println("Błędna komenda");
            }
        }

    }

    //Funkcja dodająca rangę na 15 lv
    public void Rang15lv (){
        boolean progresRanga = false;
        while (!progresRanga) {
            System.out.println("Twoja ranga wzrasta teraz jesteś Mistrzem");
            setRanga("Czempion");
            System.out.println("Wybierz specjalizację:");
            System.out.println("Wpisz \"p\" Pawężnik - większa szansa na udane parowanie, parowanie może zadać obrażenia krytyczne (większa szansa)");
            System.out.println("Wpisz \"m\" Mistrz Miecza - +6 obrażenia minimalne i max, większa szansa na zadanie obrażenia krytycznego, atak zawsze trafia");

            switch (scan.next()) {
                case "m": {
                    modyfikatorAtak += 5;
                    modyfikatorMieczKryt += 25;
                    setRanga("Champion - Mistrz Miecza ");
                    progresRanga = true;
                }
                break;
                case "p": {
                    if (modyfikatorTarczaSzansa!=0) {
                        modyfikatorTarczaSzansa += 5;
                        modyfikatorTarczakryt += 5;
                        progresRanga = true;
                    }
                    else {
                        modyfikatorTarczaSzansa +=15;
                        modyfikatorTarczakryt += 25;
                        setRanga("Champion - Pawężnik");
                        progresRanga = true;
                    }
                }
                break;
                default:
                    System.out.println("Błędna komenda");
            }
        }


    }

    //funkcja zwracająca informacje o bohaterze iloś HP, min max DMG i LV
    public void info() {
        System.out.println();
        System.out.println(getName());
        System.out.println("HP " + getCurrentHP() + "/" + getHP());
        System.out.print("DMG " + getDmgD() + "-" + getDmgU());
        if (modyfikatorAtak > 0)
            System.out.println(" + " + modyfikatorAtak);
        else
            System.out.println();
        System.out.println("LV " + getCurrentLV());
        System.out.println("Exp - " + getExp() + "/" + getLvUpPoint());
        System.out.println("Ranga " + getRanga());
        System.out.println("Mikstury - " + ilośćMikstur);
        System.out.println();
    }


    // Zmodyfikowana funkcja deal dmg dodająca exp bohaterowi po wygranej walce
    @Override
    public void dealDmg(Potwór monster) {
        int chance = rdm.nextInt(100) + 1;
        super.dealDmg(monster);
        if (!deathCheck(monster)) {
            setExp(getExp() + monster.getExp());
            if (chance >= 90) {
                ilośćMikstur++;
                System.out.println("Znalazłeś miksturę!");
            }
            LVupCheck();
        }
    }

    @Override
    public void criticalHit(Potwór monster) {
        int chance = rdm.nextInt(100) + 1;
        super.criticalHit(monster);
        if (!deathCheck(monster)) {
            setExp(getExp() + monster.getExp());
            if (chance >= 80) {
                ilośćMikstur++;
                System.out.println("Znalazłeś miksturę!");
            }
            LVupCheck();
        }
    }

    @Override
    public void parryAttackSuccess(Potwór monster, Bohater hero) {
        int chance = rdm.nextInt(100) + 1;
        super.parryAttackSuccess(monster, hero);
        if (!deathCheck(monster)) {
            setExp(getExp() + monster.getExp());
            if (chance >= 85) {
                ilośćMikstur++;
                System.out.println("Znalazłeś miksturę!");
            }
            LVupCheck();
        }

    }

    //SETERY GETERY
    public int getCurrentLV() {
        return currentLV;
    }

    public int getLvUpPoint() {
        return LvUpPoint;
    }

    public void setCurrentLV(int currentLV) {
        this.currentLV = currentLV;
    }

    public void setLvUpPoint(int lvUpPoint) {
        LvUpPoint = lvUpPoint;
    }

    public String getRanga() {
        return ranga;
    }

    public void setRanga(String ranga) {
        this.ranga = ranga;
    }


    @Override
    public int getAttackDmg() {
        return super.getAttackDmg() + modyfikatorAtak;

    }
}

