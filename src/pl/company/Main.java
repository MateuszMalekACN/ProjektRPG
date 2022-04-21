package pl.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Random przeznaczenie = new Random();
        int los = 0;
        Scanner scan = new Scanner(new File("src/pl/company/PotworyLas"));
        Scanner scane = new Scanner(new File("src/pl/company/PotworyGosciniec"));
        Scanner scaner = new Scanner(new File("C:\\Users\\mateusz.malek\\IdeaProjects\\Projekt\\out\\production\\Projekt\\pl\\company\\PotworyDwor"));
        Scanner sc = new Scanner(System.in);
        Bohater Hero = new Bohater(50, 2, 6, 0, " ");
        Historia story = new Historia();
        story.Nazywanko(Hero);
        story.rozdziałI(Hero);
        Hero.getCurrentLV();
        Hero.setCurrentLV(1);
        //Hero.setCurrentHP(1);


        List<Potwór> potworyLas = new ArrayList<>();
        List<Potwór> potworyGosciniec = new ArrayList<>();
        List<Potwór> potworyDwor = new ArrayList<>();

        while (scan.hasNext() && scane.hasNext() && scaner.hasNext()) {
            potworyLas.add(new Potwór(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.next()));
            potworyGosciniec.add(new Potwór(scane.nextInt(), scane.nextInt(), scane.nextInt(), scane.nextInt(), scane.next()));
            potworyDwor.add(new Potwór(scaner.nextInt(), scaner.nextInt(), scaner.nextInt(), scaner.nextInt(), scaner.next()));
        }
        Potwór Boss = new Potwór(500, 7, 15, 0, "MrocznyWładca");


        while (story.Progress && Hero.deathCheck(Hero)) {
            los = przeznaczenie.nextInt(potworyLas.size());
            story.Rozdziały(Hero, potworyLas.get(los), story);
        }

        if (Hero.deathCheck(Hero))
            story.rozdziałII(Hero);

        while (story.Progress && Hero.deathCheck(Hero)) {
            los = przeznaczenie.nextInt(potworyGosciniec.size());
            story.Rozdziały(Hero, potworyGosciniec.get(los), story);
        }

        if (Hero.deathCheck(Hero))
            story.rozdziałII(Hero);
        else
            System.exit(0);

        while (story.Progress && Hero.deathCheck(Hero)) {
            los = przeznaczenie.nextInt(potworyDwor.size());
            story.Rozdziały(Hero, potworyDwor.get(los), story);
        }
        if (Hero.deathCheck(Hero))
        story.Boss();
        else
        System.exit(0);

        while (Hero.deathCheck(Hero)&& Boss.deathCheck(Boss))
        story.Arena(Hero, Boss);
        if (Hero.deathCheck(Hero)) {
            System.out.println("Udało Ci się pokonać zło plugawiące Twój świat");
            System.out.println("Budzisz się nagle, to wszystko okazuje się snem, a Ty zaspałeś na Daily...");
        } else
            System.out.println("Mroczny Władca pochłania Twoją duszę");
    }
}

