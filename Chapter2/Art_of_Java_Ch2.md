# **Art_of_Java**

## **2. A Recursive-Descent Expression Parser**

당신은 (10 - 5) * 3 과 같은 숫자 식을 포함하는 문자열을 입력하여 적절한 답을 계산하는 프로그램을 어떻게 작성하나요?
만약 프로그래머들 사이에 여전히 "high priesthood" 가 있다면, 이걸 하는 방법을 아는 사람은 거의 없을 것이다.
그렇지 않으면 많은 성취한 프로그래머들은 고수준 언어가 algebraic 식을 컴퓨터가 실행할 수 있는 명령어로 변환하는 방법에 의해 mystified된다.
이 과정은 expression parsing 이라 불리고, 모든 언어 컴파일러, 인터프리터, 스프레드시트 그리고 숫자 식을 컴퓨터가 사용할 수 있는 형식으로 변환을 필요로하는 다른 것의 backbone이다.

비록 uninitiated 에게는 신비롭지만, expression parsing은 우아한 해결책이 있는 잘 정의된 task이다.
이것은 그 문제가 잘 정의되어있고 expression parsing이 algebra의 엄격한 규칙에 따라 작동하기 때문이다.
이 챕터는 recursive-descent parser로서 공통으로 참조되어있는 것을 개발하고, 모두 필요한 지원은 당신이 숫자 식을 평가할 수 있게 루틴한다.
한때, 당신은 파서의 연산자를 마스터했고, 당신은 쉽게 향상할 수 있고 당신의 필요를 맞추기 위해 수정할 수 있다.

그 자체에서 유용한 코드 조각이 되는 것 외에는, 파서는 자바 언어의 힘과 범위를 설명하기 때문에 이 책의 첫 번째 예시로 채택되었다.
파서는 "pure code" subsystem 이다.
이것에 의해, 네트워크 기반이 아니고, GUI 인터페이스에 의존하지 않고, 애플릿이나 서블릿 등도 아니라는 것을 의미한다.
자바가 아니라, C나 C++코드에서 작성된 코드 타입이다.
자바는 우리가 인터넷을 프로그램하는 방법을 근본적으로 바꿨던 혁신적인 힘이기 때문에, 우리는 그 환경에 제한되지 않았다는 것을 때때로 잊어버린다.
대신에 자바는 다른 프로그래밍 task에 가깝게 적용될 수 있는 full-featured 언어이다.

### **Expressions**

파서가 expression을 처리하기 때문에, expression이 되는 것을 이해하는 것이 필요하다.
비록 많은 다른 expression의 타입이 있지만, 이 장에서는 한 가지 타입만 다룬다: 숫자식.
우리의 목적을 위해 숫자식은 다음과 같은 항목들로 구성된다.

- 숫자
- 연산자 +, -, /, *, ^, %, =
- 괄호(Parentheses)
- 변수

여기, 연산자 ^는 거듭제곱(을 나타내고, =는 할당 연산자이다.
이 항목들은 algebra의 규칙에 따라 expression으로 결합될 수 있다.
여기 몇 가지 예시가 있다:

10 - 8
(100-5)*14/6
a + b + c
10^5
a = 10 - b

각 연산자의 우선순위를 추정해라:

```
Highest
+ - (unary)
^
* / %
+ -
=
Lowest
```

동등한 우선순위를 가진 연산자는 왼쪽에서 오른쪽으로 평가한다.

여기서 개발된 파서는 몇 가지 제약이 따를 것이다.
첫 번째, 모든 변수는 단일 문자(즉, 26개의 변수, A~Z를 사용할 수 있다)이다.
변수는 대소문자를 구분하지 않는다 (a와 A는 같은 변수로 취급된다).
두 번째, 비록 당신이 다른 값의 타입을 다루기 위해 파서를 쉽게 수정할 수 있다면, 모든 숫자 값은 double로 추정된다.
마지막으로, 논리를 명확하고 이해하기 쉽게 유지하기 위해 가장 기본적인 오류 검사만 포함된다.

### **Parsing Expressions: The Problem***

만약 당신이 expression parsing의 문제에 대해 많이 생각하지 않는다면, 당신은 그것이 간단한 일이라고 생각할 수도 있지만, 그렇지 않다.
그 문제를 더 잘 이해하기 위해, 이 sample expression을 평가해라:

10-2*3

당신은 이 식이 4와 동일하다는 것을 안다.
비록 당신이 특정한 식을 계산하는 프로그램을 쉽게 생성할지라도, 그 문제는 어떤 임의식의 올바른 정답을 주는 프로그램을 만드는 방법이다.
처음에 당신은 이와 같은 어떤 알고리즘을 생각할지도 모른다.

a = get first operand
while(operands present) {
    op = get operator
    b = get second operand
    a = a op b
}

이 접근은 첫 연산을 수행하기 위해 첫 번째 operand, operator, 그리고 두 번째 operand 를 얻고, 다음 연산을 수행하기 위해 다음 operator와 operand를 얻는다.
그러나, 만약 당신이 이 기본적인 접근을 시도한다면, 이 과정이 연산자의 우선 순위를 무시하기 때문에 10-2*3 은 4대신에 24(즉, 8*3)로 평가한다.
당신은 algebra의 규칙이 곱셈은 뺄셈 전에 해야한다고 명령하기 때문에 operand와 operator를 왼쪽에서 오른쪽으로 취급할 수 없다.
어떤 초심자들은 이 문제를 쉽게 극복할 수 있다고 생각하는데, 때때로, 매우 제한적인 경우에는 극복할 수 있다.
그러나 그 문제는 당신이 괄호, 거듭제곱, 변수, 단항 연산자 등을 추가했을 때 더 나빠진다.

비록 식을 처리하는 코드를 작성하는 많은 방법이 있지만, 여기서 개발된 한 가지는 사람에 의해 가장 쉽게 작성되는 접근법이다.
그것은 recursive-descent parser라 불리고, 이 장의 과정에서 당신은 어떻게 그 이름을 얻어졌는지 볼 것이다. (파서를 작성 시 사용되는 몇 가지 다른 메소드는 일반적으로 다른 컴퓨터 프로그램에 의해 실행되는 complex table을 사용한다.
때때로 테이블 구동 파서(table-driven parsers)라고 불린다.)

### **Parsing an Expression**

식을 분석하고 평가하는 많은 방법이 있다.
recursive-descent parser를 사용하기 위해, recursive data structures로서 식을 생각해라 - 즉, 자체로 정의되는 식이다.
만약 현재에, 우리가 식이 오직 +, -, *, /, 그리고 괄호만을 사용할 수 있다고 가정하면, 모든 식은 다음과 같은 규칙으로 정의될 수 있다.

expression -> term[+term][-term]
term -> factor[*factor][/factor]
factor -> variable, number, or (expression)

대괄호는 선택적 요소를 지정하고, ->는 생성을 의미한다.
사실, 그 규칙은 보통 식의 production rules 라 불린다.
그러므로, 용어의 정의에 대해 당신은 말할 수 있다 : "용어는 생산한다 " //
연산자의 우선 순위가 식이 정의된 방법에서 암시적라는 것을 알아채라.

여기에 한 예시가 있다.
식 `10+5*B` 는 두 용어를 가지고 있다 : 10 그리고 5*B
두 번째 용어는 두 가지 요인을 포함한다 : 5 그리고 B
이 요인들은 하나의 숫자와 하나의 변수로 구성된다.

다른 한편으로, 식 `14*(7-C)`는 두 요인을 가지고 있다 : 14 그리고 (7-C)
이 요인들은 하나의 숫자와 하나의 괄호식으로 구성된다.
그 괄호식은 두 용어를 포함한다 : 하나의 숫자와 하나의 변수.

이 과정은 recursive-descent parser의 기초를 형성하고, 이는 연쇠적으로 작동하고 생산 규칙을 구현하는 상호 재귀 메소드의 집합이다.
적절한 각 단계에서, 파서는 algebraically 올바른 순서로 특정한 연산을 수행한다.
어떻게 그 생산 규칙이 식을 분석하는데 사용되는지 보기 위해 다음의 식을 사용한 예시를 통해 작동해보자:

9/3-(100+56)

여기 따라야 하는 순서가 있다:

1. 첫 번째 용어를 받아라, 9/3
2. 각 요인을 받고 정수들을 나눠라. 결과 값은 3이다.
3. 두 번째 용어를 받아라, (100+56). 이 시점에서, 이 하위식을 재귀적으로 분석해라.
4. 각 용어를 받고 더해라. 결과 값은 156이다.
5. 두 번째 용어의 재귀적 평가로 부터 반환해라.
6. 3에서 156을 빼라. 그 답은 -153이다.

만약 당신이 이 시점에서 약간 혼란이 온다면, 나쁘지 않다.
이것은 익숙해지는데 시간이 걸리는 상당히 복잡한 개념이다.
식의 재귀적 관점에 대해 기억해야 할 두 가지 기본적인 것이 있다.
첫 번째, 연산자의 우선 순위는 생산 규칙이 정의되는 방법에 내포되어있다.
두 번째, 식을 파싱하고 평가하는 이 메소드는 사람이 수학적인 식을 평가하는 방법과 매우 비슷하다.

이 장의 나머지 부분은 2개의 파서를 개발한다.
첫째는 오직 리터럴 값으로만 구성된 double타입의 부동 소수점 표기법을 분석하고 평가할 것이다.
이 파서는 파싱의 recursive-descent 메소드의 기초를 설명한다.
둘째는 변수를 사용하는 능력을 더한다.

### **Dissecting an Expression**

식을 평가하기 위해서, 파서는 식의 개별 구성요소를 공급 받을 필요가 있다.
예를 들면, 식 `A*B-(W+10)` 은 이 개별 부분을 포함한다 : A, *, B, -, (, W, +, 10, 그리고 ).
파싱의 언어에서, 각 식의 구성요소는 토큰(token)이라 불리고, 각 토큰은 식의 불가분의 단위(unit)를 나타낸다.
파싱을 tokenizing하는 것은 파싱의 기본이기 때문에, 파서 자체를 검토하기 전에 살펴보자.

식을 토큰으로 번역하기 위해, 당신은 식에서 개별적으로 각 토큰을 연속적으로 반환하여 처음부터 끝까지 이동하는 메소드를 필요로 한다.
그 메소드는 또한 토큰의 타입을 결정하고 식의 끝을 감지할 수 있어야 한다.
여기서 개발된 파서에서, 이 작업을 수행하는 메소드는 getToken()이라 불린다.

이 장에서 두 파서 모두 Parser 클래스에서 캡슐화 되어 있다.
비록 이 클래스는 나중에 자세하게 설명되지만, 이제 첫 부분을 보여줘야 getToken()의 작업이 설명될 수 있다.
파서는 여기에 보이는 final 변수와 필드를 정의하는 것에 의해 시작한다 :

class Parser {
    // These are the token types.
    final int NONE = 0;
    final int DELIMITER = 1;
    final int VARIABLE = 2;
    final int NUMBER = 3;

    // These are the types of syntax errors.
    final int SYNTAX = 0;
    final int UNBALPARENS = 1;
    final int NOEXP = 2;
    final int DIVBYZERO = 3;

    // This token indicates end-of-expression.
    final String EOE = "\0";

    private String exp;     // refers to expression string
    private int expIdx;     // current index into the expression
    private String token;   // holds current token
    private int tokType;    // holds token's type

처음에 파서는 토큰의 타입을 나타내는 값을 정의한다.
식을 분석할 때, 각 토큰은 그것과 연관된 타입을 가지고 있어야 한다.
이 장에서 개발된 파서들에는 오직 세 가지 타입이 필요하다 : 변수, 숫자 그리고 delimiter.
이들은 VARIABLE, NUMBER, DELIMITER 값에 의해 나타난다.
DELIMITER 카테고리는 연산자와 괄호 둘 다 사용된다.
NONE 타입은 단지 정의되지 않은 토큰을 위한 placeholder값이다.

다음에, 파서는 식을 평가하고 분석할 때 발생할 수 있는 다양한 오류를 나타내는 값을 정의한다.
SYNTAX는 malformed 식을 결과로 하는 오류의 넓은 범주를 나타낸다.
UNBALPARENS는 비균형적인 괄호를 나타낸다.
NOEXP는 파서가 실행될 때 식이 없을 때 제출되는 오류이다.
DIVBYZERO는 divide-by-zero 오류를 나타낸다.

final 변수 EOE는 식의 끝에 도달했음을 나타내는 토큰이다.

분석되는 식을 잡는 문자열에 대한 참조는 exp 안에 저장된다.
그러므로, exp는 "10+4"와 같은 문자열을 참조할 것이다.
expIdx에 잡혀있는 문자열 내의 다음 토큰의 인덱스는 처음에는 0이다.
얻어진 토큰은 토큰에 저장되고, 토큰의 타입은 tokType에 저장된다.
그들이 오직 파서에 의해 사용되고 바깥 코드에 의해 수정되지 말아야되기 때문에 이들의 필드는 private이다.

getToken() 메소드는 여기에 보여진다.
호출될 때마다, expIdx에서 시작하는 exp에 의해 참조되는 문자열 안에 있는 식으로부터 다음 토큰을 얻는다.
다시 말해서, getToken()이 호출될 때마다, exp[expIdx]에서 다음 토큰을 얻는다.
그것은 토큰 필드에 이 토큰을 넣는다.
그것은 tokType 안에 토큰의 타입을 넣는다.
getToken()은 isDelim() 메소드를 사용하고, 또한 여기에 보여진다:

// Obtain the next token.
private void getToken()
{
    tokType = NONE;
    token = "";

    // Check for end of expression.
    if(expIdx == exp.length()) {
        token = EOE;
        return;
    }

    // Skip over white space.
    while(expIdx < exp.length() && Character.isWhitespace(exp.charAt(expIdx)))
        ++expIdx;

    // Trailing whitespace ends expression.
    if(expIdx == exp.length()) {
        token = EOE;
        return;
    }

    if(isDelim(exp.charAt(expIdx))) { // is operator
        token += exp.charAt(expIdx);
        expIdx++;
        tokType = DELIMITER;
    }
    else if(Character.isLetter(exp.charAt(expIdx))) { // is variable
        while(!isDelim(exp.charAt(expIdx))) {
            token += exp.charAt(expIdx);
            expIdx++;
            if(expIdx >= exp.length())
                break;
        }
        tokType = VARIABLE;
    }
    else if(Character.isDigit(exp.charAt(expIdx))) { // is number
        while(!isDelim(exp.charAt(expIdx))) {
            token += exp.charAt(expIdx);
            expIdx++;
            if(expIdx >= exp.length())
                break;
        }
        tokType = NUMBER;
    }
    else { // unknown character terminates expression
        token = EOE;
        return;
    }
}

// Return true if c is a delimiter.
private boolean isDelim(char c)
{
    if((" +-/*%^=()".indexOf(c) != -1))
        return true;
    return false;
}

getToken()을 자세히 보자.
처음 몇 번의 초기화 후에, getToken()은 expIdx와 exp.length()가 동등한지 확인하여 식의 끝에 도달했는지 확인한다.
expIdx가 분석되는 식에 대한 인덱스이기 때문에, 만약 식 문자열 길이와 같다면, 그 식은 완전히 분석된다.

만약 여전히 식에서 검색할 많은 토큰이 있다면, getToken()은 먼저 leading spaces를 건너뛴다.
만약 trailing 이 식의 끝에 위치한다면, 식의 마지막 토큰 EOE는 반환된다.
그렇지 않으면, 공백이 생략되면, exp[expIdx]는 digit, 변수, 또는 연산자 중 하나를 포함한다.
만약 다음 문자가 연산자라면, token안에 문자열로 반환되고, DELIMITER는 tokType안에 저장된다.
만약 다음 문자가 letter라면, 변수의 하나로 추정된다.
그것은 token에서 문자열로 반환되고, tokType에는 VARIABLE값이 할당된다.
만약 다음 문자가 숫자(digit)라면, 전체의 숫자는 읽어지고 token안에 문자열 형식안에 저장되고, 그것의 타입은 NUMBER이다.
마지막으로, 만약 다음 문자가 아무것도 아니라면, token은 EOE로 할당된다.

getToken()에서 코드를 명확하게 하기 위해, 오류 검사의 일정 양이 생략되고, 몇 가지 가정이 이루어졌다.
예를 들면, 어떤 인식되지 않은 문자는 공백이 선행되는 한 식을 끝낼 수 있다.
또한 이 버전에서, 변수는 어떤 길이라도 될 수 있지만, 단지 첫 letter만 중요하다.
당신의 특정 응용 프로그램이 지시하는 대로 당신은 더 많은 오류 검사를 추가할 수 있고 다른 세부 사항을 추가할 수 있다.

토큰화 과정을 더 잘 이해하려면, 다음 식에서 각 토큰과 타입이 반환하는 것을 공부해라:

`A+100-(B*C)/2`

Token   Token Type
A       VARIABLE
+       DELIMITER
100     NUMBER
-       DELIMITER
(       DELIMITER
B       VARIABLE
*       DELIMITER
C       VARIABLE
)       DELIMITER
/       DELIMITER
2       NUMBER

토큰은 단지 단일 문자를 포함하더라도, 항상 문자열을 보유한다는 것을 기억해라.
마지막 특징 : 비록 자바가 StringTokenizer클래스에 의해 지원되는 것과 같이 매우 유용한 내장 토큰화 기능을 포함하더라도, getToken()과 같이, 전용 tokenizer를 사용하여 이 작업을 직접 처리하는 것이 좋다.

### **A Simple Expression Parser**

여기 파서의 첫 번째 버전이 있다.
그것은 리터럴, 연산자, 괄호로만 구성된 식을 평가할 수 있다.
비록 getToken()이 변수를 처리할 수 있지만, 파서는 변수를 처리하지 않는다.
당신이 이 단순화된 파서가 작동하는 방법을 이해한다면, 우리는 변수를 처리하는 기능을 추가할 것이다.

/*
    This module contains the recursive descent
    parser that does not use variables.
*/

// Exception class for parser errors.
class ParserException extends Exception {
    String errStr; // describes the error

    public ParserException(String str) {
        errStr = str;
    }

    public String toString() {
        return errStr;
    }
}

class Parser {
    // These are the token types.
    final int NONE = 0;
    final int DELIMITER = 1;
    final int VARIABLE = 2;
    final int NUMBER = 3;
    
    // These are the types of syntax errors.
    final int SYNTAX = 0;
    final int UNBALPARENS = 1;
    final int NOEXP = 2;
    final int DIVBYZERO = 3;

    // This token indicates end-of-expression.
    final String EOE = "\0";
    
    private String exp;     // refers to expression string
    private int expIdx;     // current index into the expression
    private String token;   // holds current token
    private int tokType;    // holds token's type

    // Parser entry point.
    public double evaluate(String expstr) throws ParserException
    {
        double result;
        exp = expstr;
        expIdx = 0;
        
        getToken();
        if(token.equals(EOE))
            handleErr(NOEXP); // no expression present
        
        // Parse and evaluate the expression.
        result = evalExp2();

        if(!token.equals(EOE)) // last token must be EOE
            handleErr(SYNTAX):

        return result;
    }

    // Add or subtract two terms.
    private double evalExp2() throws ParserException
    {
        char op;
        double result;
        double partialResult;

        result = evalExp3();
        
        while((op = token.charAt(0)) == '+' || op == '-') {
            getToken();
            partialResult = evalExp3();
            switch(op) {
                case '-':
                    result = result - partialResult;
                    break;
                case '+':
                    result = result + partialResult;
                    break;
            }
        }
        return result;
    }

    // Multiply or divide two factors.
    private double evalExp3() throws ParserException
    {
        char op;
        double result;
        double partialResult;

        result = evalExp4();

        while((op = token.charAt(0)) == '*' || op == '/' || op == '%') {
            getToken();
            partialResult = evalExp4();
            switch(op) {
                case '*':
                    result = result * partialResult;
                    break;
                case '/':
                    if(partialResult == 0.0)
                        handleErr(DIVBYZERO);
                    result = result / partialResult;
                    break;
                case '%':
                    if(partialResult == 0.0)
                        handleErr(DIVBYZERO);
                    result = result % partialResult;
                    break;
            }
        }
        return result;
    }

    // Process an exponent.
    private double evalExp4() throws ParserException
    {
        double result;
        double partialResult;
        double ex;
        int t;

        result = evalExp5();

        if(token.equals("^")) {
            getToken();
            partialResult = evalExp4();
            ex = result;
            if(partialResult == 0.0) {
                result = 1.0;
            }
            else {
                for(t=(int)partialResult-1; t>0; t--)
                    result = result * ex;
            }
            return result;
        }

        // Evaluate a unary + or -.
        private double evalExp5() throws ParserException
        {
            double result;
            String op;

            op = "";
            if((tokType == DELIMITER) && token.equals("+") || token.equals("-")) {
                op = token;
                getToken();
            }
            result = evalExp6();
            
            if(op.equlas("-"))
                result = -result;

            return result;
        }

        // Process a parenthesized expression.
        private double evalExp6() throws ParserException
        {
            double result;

            if(token.equals("(")) {
                getToken();
                result = evalExp2();
                if(!token.equals(")"))
                    handleErr(UNBALPARENS);
                getToken();
            }
            else result = atom();

            return result;
        }

        // Get the value of a number.
        private double atom() throws ParseException
        {
            double result = 0.0;

            switch(tokType) {
                case NUMBER:
                    try {
                        result = Double.parseDouble(token);
                    } catch (NumberFormatException exc) {
                        handleErr(SYNTAX);
                    }
                    getToken();
                    break;
                default:
                    handleErr(SYNTAX);
                    break;
            }
            return result;
        }

        // Handle an error.
        private void handleErr(int error) throws ParserException
        {
            String[] err = {
                "Syntax Error",
                "Unbalanced Parentheses",
                "No Expression Present",
                "Division by Zero"
            };

            throw new ParserException(err[error]);
        }

        // Obtain the next token.
        private void getToken()
        {
            tokType = NONE;
            token = "";
            
            // Check for end of expression.
            if(expIdx == exp.length()) {
                token = EOE;
                return;
            }

            // Skip over white space.
            while(expIdx < exp.length() && Character.isWhitespace(exp.charAt(expIdx)))
                ++expIdx;

            // Trailing whitespace ends expression.
            if(expIdx == exp.length()) {
                token = EOE;
                return;
            }

            if(isDelim(exp.charAt(expIdx))) { // is operator
                token += exp.charAt(expIdx);
                expIdx++;
                tokType = DELIMITER;
            }
            else if(Character.isLetter(exp.charAt(expIdx))) { // is variable
                while(!isDelim(exp.charAt(expIdx))) {
                    token += exp.charAt(expIdx);
                    expIdx++;
                    if(expIdx >= exp.length())
                        break;
                }
                tokType = VARIABLE;
            }
            else if(Character.isDigit(exp.charAt(expIdx))) { // is number
                while(!isDelim(exp.charAt(expIdx))) {
                    token += exp.charAt(expIdx);
                    expIdx++;
                    if(expIdx >= exp.length()) break;
                }
                tokType = NUMBER;
            }
            else { // unknown character terminates expression
                token = EOE;
                return;
            }
        }

        // Return true if c is a delimiter.
        private boolean isDelim(char c)
        {
            if((" +-/*%^=()".indexOf(c) != -1))
                return true;
            return false;
        }
    }

    ParserException 클래스가 코드의 정상에 가깝게 선언된 것을 알아채라.
    이것은 식을 처리하는 동안 오류를 마주한다면 파서에 의해 던져 질 예외의 타입이다.
    이 예외는 파서를 사용하는 코드에 의해 다뤄질 필요가 있을 것이다.

    그것을 보여주는 파서는 다음 연산자들을 다룰 수 있다 : +,-,*,/,%.
    게다가, 정수 거듭제곱(^)과 unary minus를 다룰 수 있다.
    그 파서는 괄호를 올바르게 다룰 수 있다.

    파서를 사용하려면, 처음에 Parser타입의 객체를 생성해라.
    그러면 당신이 인수로서 평가되길 원하는 식 문자열을 passing하는  evaluate()를 호출해라.
    그 결과는 반환된다.
    왜냐하면, Parser는 ParserException을 던지기 때문에, 당신의 application은 각 예외를 다룰 필요가 있다.
    다음 예시는 그 파서를 입증한다:

    // Demonstrate the parser.
    import java.io.*;

    class PDemo {
        public static void main(String[] args) trows IOException
        {
            String expr;

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Parser p = new Parser();

            System.out.println("Enter an empty expression to stop.");

            for(;;) {
                System.out.print("Enter expression : ");
                expr = br.readLine();
                if(expr.equals(""))
                    break;
                try {
                    System.out.println("Result: " + p.evaluate(expr));
                    System.out.println();
                } catch (ParserException exc) {
                    System.out.println(exc);
                }
            }
        }
    }

    여기 실행 예시가 있다:

    Enter an empty expression to stop.
    Enter expression : 10-2*3
    Result : 4.0

    Enter expression : (10-2)*3
    Result : 24.0

    Enter expression : 10/3.5
    Result : 2.857142857142857
    
#### Understanding the Parser

이제 Parser를 자세하게 보자.
평가된 식을 포함하는 문자열은 exp로 참조된다.
이 필드는 evaluate()가 호출될 때마다 설정된다.
파서가 기존 자바 문자열에 포함되어 있는 식을 평가한다는 것을 기억하는 것이 중요하다.
예를 들면, 다음 문자열은 파서가 평가할 수 있는 식을 포함한다:

"10-5"
"2*3.3/(3.1416*3.3)"

exp의 현재 인덱스는 expIdx에 저장된다.
파싱이 실행을 시작할 때, expIdx는 0으로 설정된다.
expIdx는 파서가 식을 통해 이동함으로써 증가된다.
token필드는 현재 토큰을 가지고, tokType은 토큰 타입을 포함한다.

파서로의 진입점은, 분석되는 식을 포함하는 문자열과 함께 호출되는, evaluate()를 통한다.
atom()을 따라 evalExp2()부터 evalExp6()메소드는 recursive-descent parser를 형성한다.
그들은 더 빨리 언급된 식 생산 규칙의 향상된 집합을 구현한다.
각 메소드의 top에서 comment는 그들이 수행한 기능을 규정한다.
파서의 다음 버전에서, evalExp1()로 호출된 메소드는 또한 추가될 것이다.

handleErr() 메소드는 식에서 문법 오류를 다룬다.
getToken()과 isDelim() 메소드는 


### 몇 가지 시도할 것들

이 장에서 보여지는 식 파서는 그들이 당신의 파트에서 중요한 노력없이 당신이 확장되는 기능을 제공하는 것을 가능하게 하기 때문에 애플리케이션의 다양성에서 꽤 유용하다.
당신의 프로그램이 사용자가 숫자 값을 입력하는 것을 요청하는 상황을 고려해라.
예를 들면, 애플리케이션은 사용자에게 출력할 문서의 복사본의 숫자를 입력하라고 요청할 것이다.
일반적으로, 당신은 간단히 텍스트 필드를 전시할 것이고, 입력을 기다리고, 그 다음 텍스트를 내부의 숫자 형식으로 변환한다.
이 간단한 접근은 사용자가 100과 같은 값을 입력하는 것을 허락할 것이다.
그러나, 만약 사용자가 각 9개의 부분에서 72장 출력하기를 원한다면 어떻게 될까? 
그 사용자는 그 제품을 메뉴얼대로 처리하는 것을 필요로 할 것이고, 그 다음 648을 텍스트 필드에 입력할 것이다.
그러나, 만약 당신이 텍스트 필드로부터 얻어지는 값을 계산하기 위해 파서를 사용한다면, 그 사용자는 9*72를 입력할 수 있고, 매뉴얼 계산은 요청되지 않을 것이다.
숫자 식을 분석하고 평가하는 능력은 세련되고 전문적인 느낌을 간단한 애플리케이션에 추가할 수 있다.
당신의 애플리케이션의 하나에 숫자 입력을 다루는 파서를 사용하는 것을 시도해라.

이 장에서 처음에 말했듯이, 오직 최소의 오류 검사는 파서에 의해 처리된다.
당신은 세부적인 오류 제출을 원할 것이다.
예를 들면, 당신은 오류가 검출되는 식에서 그 점을 강조할 수 있다.
이것은 사용자가 문법 오류를 찾고 정정하는 것을 허락할 것이다.

이제 파서가 있으므로서 그것은 오직 숫자 식을 평가할 수 있다.
그러나, 문자열, spatial coordinates, 또는 복잡한 숫자들과 같은 식의 다른 유형을 평가할 수 있다.
예를 들면, 파서가 문자열을 평가하는 것을 허락하기 위해, 당신은 다음과 같은 변화를 만들어야한다:

1. STRING 을 호출하는 새로운 토큰 타입을 정의해라
2. 그것이 문자열을 인식할 수 있도록 getToken()을 향상시켜라.
3. STRING 토큰을 다루는 atom()에 새로운 case를 추가해라.

이 단계들을 구현한 후에, 파서는 이와 같은 문자열 식을 다룰 수 있다.:

a = "one"
b = "two"
c = a + b

c에서 그 결과는 a와 b의 연속이 되어야 한다, 또는 "onetwo"