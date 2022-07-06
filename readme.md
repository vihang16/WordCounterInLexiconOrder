## Application to find occurrences of word in lexicon order with it appearances in sentence number
this is an application which breaks English sentence into case-insensitive words in lexicon order. Along with that it will store sentence number it appeared. We can calculate frequency of the word by invoking `size()` method of the list. 
Below are the technologies

| Name     | Version        |
|----------|----------------|
| Java     | Correto-17.0.3 |
| JUnit    | 5              |
| IntelliJ |IDEA 2022.1.1 (Community Edition)                |

### How to run the program:
- Download Java version 17.0.3 for [windows](https://corretto.aws/downloads/latest/amazon-corretto-17-x64-windows-jdk.msi) or for [Linux]( https://corretto.aws/downloads/latest/amazon-corretto-17-x64-linux-jdk.tar.gz) with `wget` command.
- Download intelliJ community edition from [site](https://www.jetbrains.com/idea/download/#section=windows).
- Import project from `git` tab
- Go to file `WordCounterTest` right click near to class name and click on `Run` button.


### Limitations:
- This code only works for grammatically correct English sentence. i.e. if sentence starts with small letter or without space after period code would consider that as single sentence.
- It would remove last period from abbreviation i.e. `i.e.` would be replaced with `i.e`. 



