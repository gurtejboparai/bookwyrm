//Character sequences that have no legitimate reason to be used, these are dead giveaways to malicious purpose
//const GIVEAWAYS = ["src="]

//html tags known to be common attack vectors, however these might appear if somebody makes a typo
const MAL_TAGS = ["<script", "<img", "<body", "<input", "<iframe"]
//the matching escape tags for the above, these share matching indexes save for the universal escape "/>"
const TAG_ESC = ["<script/>", "<img/>", "<body/>", "<input/>", "<iframe/>", "/>"]

export default{
    
    sanitize(dirty){
        var attempts = 0;
        var clean = dirty;
        //Because html isn't case sensitive I'll have to make a lowercase version 
        //and copy identical changes onto a copied string
        var suspect = dirty.toLowerCase();
        var safe = false;
        //Yes I know it's a dead meme, but it works as a variable name
        var sus = false;

        while(!safe & attempts < 5){

            var began = 0;
            var stops = 0;
            var idx = 0;
            sus = false;

            //Go throught the list of malicious tags and check for each one in the suspect string
            while(idx < MAL_TAGS.length & !sus){
                began = suspect.indexOf(MAL_TAGS[idx]);
                sus = began >= 0;

                if(sus){
                    //Look for the matching termination
                    stops = suspect.lastIndexOf(TAG_ESC[idx]);
                    if(stops < 0){
                        //If the specific terminating tag couldn't be found than we assume the
                        //universal terminator was used
                        stops = suspect.indexOf(TAG_ESC[TAG_ESC.length-1], began); 
                        if(stops < 0){
                            //if it doesn't have a termination we'll just delete the tag
                            stops = began + MAL_TAGS[idx].length;
                        }
                        else{
                            stops += TAG_ESC[TAG_ESC.length-1].length;
                        }
                    }
                    else{
                        stops += TAG_ESC[idx].length;
                    }
                }
                else{

                    idx++;
                }
            }
            if(sus){
                //there is something to delete
                suspect = this.gut(suspect, began, stops);
                clean = this.gut(clean, began, stops);
                attempts++;
            }
            else{
                //if nothing could be found than we will say it's safe
                safe = true;
            }
        }

        //In order to ensure no infinite loops we'll give up after a certain number of attempts
        //But that doesn't mean I'm letting malicious code through
        if(!safe){
            clean = "Nice try"
        }
        return clean
    },

    /*The method "gut" takes a string and two positions in that string it removes the characters
    between those positions and returns a string that is the characters before the removed 
    concatonated to the characters after that section
    In other words its called "gut" because it takes out the middle part*/
    gut(thing, start, final){
        var husk = thing.substring(0, start);
        husk = husk.concat(thing.substring(final, thing.length));
        return husk;
    }

}