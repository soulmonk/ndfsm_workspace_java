package com.soulmonk.ndfsm.appConsole;
abstract class WebForm<T extends WebForm<T>> {
    static final String NAME = "WEBFORM";

    String getName() {
        return T.NAME;
    }
}

class DistributionWebForm extends WebForm<DistributionWebForm> {
    static final String NAME = "DISTRIBUTION_WEB_FORM";
}

public class TwoNames {
    public static void main(String[] args) {
        DistributionWebForm d = new DistributionWebForm();
        System.out.println("d.NAME: " + d.NAME);
        //BUT...
        System.out.println("d.getName: " + d.getName());
    }
}