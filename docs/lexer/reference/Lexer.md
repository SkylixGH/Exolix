# Class: `Lexer`
Create a lexical analyzer. A lexer is a device that reads a string and list of valid tokens and attempts to match them in a special way to generate so called tokens. These tokens can contain information such as where they are located, and what they represent.

 - **Type Args**
   - `Structure extends Object` The data structure enforcement of the tree. This is important so that you get type support for token types with supported IDEs and editors.

## Constructor
 - **Args**
   - `tree` : `Structure` The data structure for the lexer. This information contains all of the valid usable match patterns for the lexer. Do note, if a token with the RegExp value contains the escape character, line line tracking will be supported.'
   - `input` : `string | buffer` The input data of the lexer.

## Async Function: `tokenize`
This function will start to tokenize the lexer data, once completed, the promise will be resolved. If you attempt to read the tokens before the tokenization has not finished, you will receive the incomplete list of tokens. Running the tokenize function after it has run once will not result in any tokenizations.

## Property: `tokens` : [`Token`](./Token.md)`<Structure>` __readonly__
All of the generated tokens, this list will only be fully completed once the tokenization has finished.

## Property: `input` : `string` __readonly__
The input that the lexer received while being constructed.

## Property: `tree` : `Structure` __readonly__
The input structure of the lexer that was received during construction. This contains all of the valid tokens and their RegExp match patterns.

## Property: `newLine` : `strings` __readonly__
The new line characters that the lexer will use to know where the analyzer window is at.
