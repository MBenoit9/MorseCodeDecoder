# MorseCodeDecoder
Find all possible translations of a single string (no spaces) of morse code

When prompted enter a morse code message with 'A' being a dash and 'O' being a dot. The prgoram will print all possible translations of the morse code.

The problem behind this code is that there is a captain at sea sending out an SOS message. He is in some really deep clamshells (if you know what I mean). Because of this, the captain is unable to make pauses when sending the message. So, the translation has to find all the possible messages that the captain could be trying to send.

My task was to incorporate dynamic programming which I did with a lookup table so that already translated substrings would not need to be translated again.
