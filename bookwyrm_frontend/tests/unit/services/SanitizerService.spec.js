import SanitizerService from "@/services/SanitizerService";

var attacks = [
    "I'm<script src=http://Evil.com/> not evil",
    "Honestly, I'm<iFrAme> not<ifRamE/> evil",
    "No, for real I'm a good string that isn't hiding anything from anyone"
];

var answers = [
    "I'm not evil",
    "Honestly, I'm evil",
    "No, for real I'm a good string that isn't hiding anything from anyone"
];
test("runs the sanitizer against various strings that may contain XSS attacks", ()=>{
    for(var idx=0; idx<attacks.length; idx++){
        expect(SanitizerService.sanitize(attacks[idx])).toMatch(answers[idx]);
    }
});

