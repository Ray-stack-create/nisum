package com.example;


public class TextEditorSetter {
    private SpellChecker spellChecker;
    public void setSpellChecker(SpellChecker spellChecker) {
        System.out.println("TextEditorSetter: setSpellChecker called.");
        this.spellChecker = spellChecker;
    }

    public void spellCheck() {
        spellChecker.checkSpelling();
    }
}
