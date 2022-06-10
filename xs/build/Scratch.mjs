import { Lexer } from "@skylixgh/elixor-lexer";
const tokenTree = {
  leftParenthesis: "^(",
  rightParenthesis: "^)",
  quote: '^"',
  text: '^[^"^(^)]+'
};
const processor = new Lexer(tokenTree, `print("Hello World"`);
