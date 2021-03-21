package org.hn.main.service.dataprovider.gitignorelocal;

import org.hn.main.service.dataprovider.IgnoreDataProvider;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GitIgnoreDataProviderTest {

    private final String smallGitIgnoreIOJson = "{\n" +
            "  \"monodevelop\": {\n" +
            "    \"key\": \"monodevelop\",\n" +
            "    \"name\": \"MonoDevelop\",\n" +
            "    \"contents\": \"\\n### MonoDevelop ###\\n#User Specific\\n*.userprefs\\n*.usertasks\\n\\n#Mono Project Files\\n*.pidb\\n*.resources\\ntest-results\\/\\n\",\n" +
            "    \"fileName\": \"MonoDevelop.gitignore\"\n" +
            "  },\n" +
            "  \"flashbuilder\": {\n" +
            "    \"key\": \"flashbuilder\",\n" +
            "    \"name\": \"FlashBuilder\",\n" +
            "    \"contents\": \"\\n### FlashBuilder ###\\n# Build and Release Folders\\nbin\\/\\nbin-debug\\/\\nbin-release\\/\\n.actionScriptProperties\\n.flexConfig.xml\\n\\n# only for a non-library project\\n.flexProperties\\n\\n# only for library project\\n.flexLibProperties\\n\\n# Other files and folders\\n.settings\\/\\n.project\\/\\n\\n.FlexUnitSettings\\n.externalToolBuilders\\n.model\\n\\n\",\n" +
            "    \"fileName\": \"FlashBuilder.gitignore\"\n" +
            "  },\n" +
            "  \"racket\": {\n" +
            "    \"key\": \"racket\",\n" +
            "    \"name\": \"Racket\",\n" +
            "    \"contents\": \"\\n### Racket ###\\n# gitignore template for the Racket language\\n# website: http:\\/\\/www.racket-lang.org\\/\\n\\n# DrRacket autosave files\\n*.rkt~\\n*.rkt.bak\\n\\\\#*.rkt#\\n\\\\#*.rkt#*#\\n\\n# Compiled racket bytecode\\ncompiled\\/\\n*.zo\\n\\n# Dependency tracking files\\n*.dep\\n\",\n" +
            "    \"fileName\": \"Racket.gitignore\"\n" +
            "  }}";


    private IgnoreDataProvider getDataProvider() {
        return new GitIgnoreDataProvider(new ByteArrayInputStream( this.smallGitIgnoreIOJson.getBytes() ));
    }

    @Test
    public void testExistingKeys() {
        var map1 = getDataProvider().getIgnoreContents(List.of("monodevelop"));
        var map2 = getDataProvider().getIgnoreContents(List.of("monodevelop", "flashbuilder", "racket"));
        assertEquals(1, map1.keySet().size());
        assertEquals(3, map2.keySet().size());
    }

    @Test
    public void testPartiallyExistingKeys() {
        var map1 = getDataProvider().getIgnoreContents(List.of("racket", "nonexisting"));
        var map2 = getDataProvider().getIgnoreContents(List.of("nonexisting", "monodevelop"));
        assertEquals(1, map1.keySet().size());
        assertEquals(1, map2.keySet().size());
    }

    @Test
    public void testNonExistingKeys() {
        var map1 = getDataProvider().getIgnoreContents(List.of("nonexisting", "nonexisting"));
        var map2 = getDataProvider().getIgnoreContents(List.of("nonexisting1", "nonexisting2", "nonexisting3", "nonexisting4"));
        var map3 = getDataProvider().getIgnoreContents(List.of("nonexisting1"));
        var map4 = getDataProvider().getIgnoreContents(List.of());

        assertEquals(0, map1.keySet().size());
        assertEquals(0, map2.keySet().size());
        assertEquals(0, map3.keySet().size());
        assertEquals(0, map4.keySet().size());
    }

    @Test
    public void testDuplicatedKeys() {
        var map1 = getDataProvider().getIgnoreContents(List.of("racket", "racket", "racket", "racket", "racket"));
        var map2 = getDataProvider().getIgnoreContents(List.of("monodevelop", "monodevelop"));
        var map3 = getDataProvider().getIgnoreContents(List.of("monodevelop", "monodevelop", "flashbuilder"));

        assertEquals(1, map1.keySet().size());
        assertEquals(1, map2.keySet().size());
        assertEquals(2, map3.keySet().size());
    }
}