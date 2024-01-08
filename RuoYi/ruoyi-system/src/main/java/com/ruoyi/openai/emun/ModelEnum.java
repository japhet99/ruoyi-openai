package com.ruoyi.openai.emun;

public enum ModelEnum {

    GPT_3_5_TURBO("gpt-3.5-turbo"),
    GPT_3_5_TURBO_0301("gpt-3.5-turbo-0301"),
    GPT_3_5_TURBO_16k("gpt-3.5-turbo-16k"),
    GPT_4("gpt-4"),
    GPT_4_0314("gpt-4-0314"),
    GPT_4_32K("gpt-4-32k"),
    GPT_4_32K_0314("gpt-4-32k-0314"),
    GPT_4_TURBO("gpt-4-1106-preview");

    private String name;

    public String getName() {
        return this.name;
    }

    private ModelEnum(String name) {
        this.name = name;
    }
}
