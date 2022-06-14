// import { Lexer, TokenUtil, TokenLocation } from "@skylixgh/elixor-lexer";

// Error.stackTraceLimit = Infinity;

// const tokens: {
//     newLine: RegExp;
//     lParen: RegExp;
//     rParen: RegExp;
//     semi: RegExp;
//     space: RegExp;
//     number: RegExp;
//     text: RegExp;
// } = {
//     newLine: /^\n/,
//     lParen: /^\(/,
//     rParen: /^\)/,
//     semi: /^;/,
//     space: /^\s+/,
//     number: /^[0-9]+/,
//     text: /^[^\n\(\);]+/,
// }

// let lexer: Lexer<typeof tokens>;

// try {
// lexer = new Lexer(tokens, `print(1);
// print(2123123);   
// print(This can print text sad\as67sa5%%^&*d\as\d\asd\asd);


















// melon-fruit(askjdkjasd);`);
// await lexer.tokenize();

// } catch (e) {
//     console.log(e.message);
//     process.exit(1);
// }
// console.log(lexer.tokens.length + " tokens");
// const util = new TokenUtil(lexer);
// const data = lexer.tokens;

// let context: null | string = null;
// let meta: any = {};
// let storage = "";
// const ast = [] as any[];

// console.log(data);

// data.forEach((token, index) => {
//     const location: TokenLocation = {
//         indexCharStart: token.start
//     };

//     if (!context) {
//         if (token.type === "text") {
//             context = "text";
//             storage = token.value;
//         }
//     } else {
//         if (context == "text") {
//             if (token.type === "lParen") {
//                 context = "func:open";

//                 meta = {
//                     type: "function",
//                     name: storage,
//                 };

//                 storage = "";
//             }
//         } else if (context == "func:open") {
//             if (token.type === "number") {
//                 context = "func:close";

//                 meta.args = [{
//                     type: "int",
//                     value: +token.value
//                 }];
//             } else if (token.type === "text") {
//                 context = "func:close";

//                 meta.args = [{
//                     type: "string",
//                     value: token.value
//                 }];
//             }
//         } else if (context == "func:close") {
//             context = "func:term";
//         } else if (context == "func:term") {
//             if (token.type === "semi") {
//                 ast.push(meta);
//                 meta = null;
//                 context = null;
//             } else {
//                 console.error("Syntax error, missing semicolon");
//                 process.exit(1);
//             }
//         }
//     }
// });

// console.log(ast);

// ast.forEach((node) => {
//     if (node.type === "function") {
//         if (node.name === "print") {
//             console.log(node.args[0].value);
//         } else if (node.name === "melon-fruit") {
//             console.log(node.args[0].value + " <<-- Is a melon fruit certified gamer");
//         } else {
//             console.error("Unknown function: " + node.name);
//             process.exit(1);
//         }
//     }
// })

import { Server } from "@skylixgh/elixor-server";

const s = new Server();

s.run();
