# **Art_of_Java**

## **3. Implementing Language Interpreters in Java**

당신의 컴퓨터 언어를 생성하기를 원했던 적이 있는가?
만약 당신이 대부분의 프로그래머 같다면, 당신은 아마도 가지고 있을 것이다.
솔직히, 당신의 컴퓨터 언어를 생성, 제어, 향상, 그리고 수정할 수 있게 되는 생각은 매우 매력적이다.
그러나, 그것이 얼마나 쉽고 즐거울 수 있는지를 깨닫는 프로그래머는 거의 없다.
자바와 같이 완전한 기능을 갖춘 컴파일러의 개발이 주요 사업이지만, 언어 인터프리터의 생성이 더 간단한 작업이다.

인터프리터와 컴파일러 둘 다 프로그램의 소스 코드를 입력함으로서 취해지지만, 그 소스 코드로 하는 일이 상당히 다르다.
컴파일러는 프로그램의 소스 코드를 실행 가능한 형식으로 변환한다.
종종, C++과 같은 언어 같은 경우로서, 이 실행 가능한 형식은 컴퓨터에 의해 직접적으로 실행되는 실제의 CPU명령어로 구성된다.
다른 경우에는, 컴파일러의 출력은 휴대용 중간 형식이고, 그 다음 런타임 시스템에 의해 실행된다.
이것은 자바의 작동 방식이다.
자바에서 이것의 중간 코드는 바이트 코드(bytecode)라 불린다.

인터프리터는 전혀 다른 방식으로 동작한다.
그것은 마주함으로써 각 문장을 실행하면서, 프로그램에 소스 코드를 읽는다.
그러므로, 인터프리터는 소스 코드를 객체 코드로 번역하지 않는다.
대신에, 인터프리터는 직접 프로그램을 실행한다.
인터프리터를 통해 프로그램 실행은 컴파일된 형식에서 같은 프로그램이 실행될 때보다 느리지만, 인터프리터는 다음 이유로 여전히 흔하게 프로그래밍에서 사용된다.

첫 번째, 그들은 프로그램 실행이 중지될 수 있고  사용자 대화 방식을 통해 재개될 수 있는 상호적 환경을 진정으로 제공할 수 있다.
예를 들면, 각 상호적 환경은 로봇 공학에서 도움이 된다.
두 번째, 언어 인터프리터의 성격 때문에, 그들은 특히 상호적인 디버깅에 적합하다.
세 번째, 인터프리터는 데이터베이스에 쿼리 언어로서 "스크립트 언어"에 훌륭하다.
네 번째, 그들은 같은 프로그램이 다른 다양한 플랫폼에서 실행하는 것을 허락한다.
오직 인터프리터의 런타임 패키지는 각 새로운 환경으로 구현되어야 한다.

때때로 interpreter 용어는 단지 규정되는 것보다 다른 상황에서 사용된다.
예를 들면, 기존 자바 런타임 시스템은 bytecode interpreter 라고 불렸다.
이것은 이 장에서 개발된 인터프리터의 같은 타입이 아니다.
자바 런타임 시스템은 바이트 코드를 위한 실행 환경을 제공하고, 휴대용 기계 명령어의 집합으로 매우 최적화 된다.
그러므로, 자바 런타임 시스템은 소스 코드에서 작동하지 않고, 휴대용 기계 코드에서 동작한다.
이것은 자바 런타임 시스템이 자바 가상 기계(Java Virtual Machine)라 불리는 이유이다.

흥미있고 유용한 코드 조각이 되는 것에 더하여, 이 장에서 개발된 인터프리터는 두 개의 목적을 제공한다:
능률한 자바 언어의 우아함을 입증한다.
2장에서 파서처럼, 언어 인터프리터는 "pure code" 예시이다.
그것은 또한 상당히 세련된 프로그램이다.
자바에서 인터프리터가 구현될 수 있는 것에 의한 그 쉬움은 증거testimony를 자바의 다재다능으로 준다.
게다가, 그 코드의 투명함은 자바 문법과 라이브러리의 표현적인 힘을 보여준다.

### What Computer Language to Interpret?

우리가 인터프리터를 설계할 수 있기 전에, 우리가 통역을 원하는 언어를 선택하는 것이 필수이다.
자바가 명백한 선택으로 보이지만, 너무 크고 세련된 언어이다.
자바 언어의 작은 하위 집합에 인터프리터를 위한 그 소스 코드는 이 책의 한 챕터로 맞춰지는 것이 꽤 크게 될 것이다!
게다가, 당신은 일반적으로 자바만큼 강력한 언어로 인터프리터를 작성하는 것이 필요하지 않다.



중간 생략



PRINT "A Simple Small BASIC Program"

FOR X = 1 TO 10
GOSUB 100
NEXT

END

100 PRINT X
RETURN

프로그램은 다음의 출력을 소개한다:
A Simple Small BASIC Program
1.0
2.0
3.0
4.0
5.0
6.0
7.0
8.0
9.0
10.0

비록 Small BASIC 키워드들이 의미하는 것이 거의 직관적일지라도, 이 장에서 나중에 각각 충분히 설명된다.
마지막 한 포인트 : Small BASIC은 몇 년 전에, Visual Basic이 아니라, BASIC의 기본 버전의 본을 따라 패턴이 형성되었다.
Visual Basic은 매우 흔하지 않은 기본 BASIC 언어를 가지고 있다.
물론, 당신이 어떻게 인터프리터가 작동하는지 이해한 때에, 어떤 언어나 당신이 열망하는 변종일지라도 당신은 그것을 인터프리터로 바꿀 수 있다.

### An Overview of the Interpreter

처음에는(at the outset) 이 장에서 개발된 인터프리터가 소스 코드 인터프리터(source code interpreter)라는 것을 다시 강조하는 것이 필수적이다.
이것은 그것이 한 번에 한 문장씩 소스 코드를 읽으면서 프로그램을 실행하고, 그것을 실행하므로써 각 특정한 연산자를 수행하는 것을 의미한다.
이것은 바이트 코드를 분석하는 기본 자바 런타임 시스템과 같은 pseudo-code interpreter 와 다르다.
그 차이는 소스 코드 인터프리터가 프로그램의 소스코드에서 직접적으로 수행한다.
pseudo-code interpreter 는 컴파일러에 의해 기계 독립적인 중간 코드로 변환된 후에 프로그램을 실행한다.
소스 코드 인터프리터는 생성하는게 쉽고, 독립된 편집 공간을 요구하지 않는다.

Small BASIC 인터프리터는 두 개의 주된 하위시스템을 포함한다:
수식 파서는 숫자 식을 다루고, 인터프리터는 실제로 프로그램을 실행한다.
그 수식 파서는 챕터2에 보여지는 한가지로부터 적용된다.
그것이 여기 사용되면서, 그것은 수식 파서를 분석한다.


### The Small BASIC Expression Parser

인터프리터의 핵심은 수식 파서이다.
Small BASIC에 의해 사용된 파서는 챕터2에서 보여진 것으로부터 적용된다.
만약 당신이 챕터2를 아직 읽지 않았다면, 파서의 설명이 자세하게 제공되어 있기 때문에 지금 챕터2를 읽어라.
비록 그것의 근본적인 연산은 바뀌지 않지만, 파서의 특별한 버전은 Small BASIC이 요구된다.

파서에서 많은 변화는 Small BASIC 언어의 문법을 다루는 것을 허락한다.
예를 들면, 파서는 언어의 키워드를 인지할 수 있어야 하고, 연산자처럼 '=' 등호를 다루지 않고, 관계 연산자를 평가할 필요가 있다.
getToken() 메소드는 그것에 배치된 확장된 요구들을 다루기 위해 상당히 향상된다.

챕터2에서 파서와 여기에 사용된 것 사이의 차이는 효율성 고려에 의해 야기된다.
예를 들면, 2장에서, 식의 참조는 파서를 지나친다.
Small BASIC 버전에서, 해석되는 중인 프로그램의 참조는 인터프리터와 파서 둘에 의해 공유된 인스턴스 변수에 잡혀있다.
그러므로, 기준 통과와 관련된 오버헤드는 피할 수 있다.
인터프리터가 본래 느리기 때문에, 이러한 유형의 효율 향상은 중요하다.

파서에서 또 다른 변화는 Small BASIC 버전이 문자열보다 오히려 문자 배열에서 작동한다는 사실에 의해 야기된다.
2장에서 개발된 파서가 평가하는 식을 포함하는 문자열로 넘어가는 것을 회상해라.
그 변화의 이유는 효율성이다.
당신도 알다시피, 프로그램은 문자열이 아니라, 문자의 연속인 일반적인 텍스트 파일에 저장된다.
그러므로, 인터프리터가 실행 이전의 파일을 불러올 때, 파일을 문자 배열로 읽는다.
비록 이 배열을 문자열로 변환할 수 있더라도, 그렇게 하는것은 불필요한 비효율을 소개할 것이다.

Small BASIC 수식 파서가 2장에서 규정된 같은 기술을 사용한 후로, 당신은 그것의 연산자에 따른 트러블을 가지지 않을 것이고, 우리는 여기에서 그것을 자세하게 검사하지 않을 것이다.
그러나, 인터프리터로 이동하기 전에, 몇 가지 일반적인 언급이 순서대로 들어있다.
우리는 수식이 Small BASIC과 관련있는 것을 확실히 설명하면서 시작할 것이다.

#### Small BASIC Expressions

그들이 이 장에서 개발된 Small BASIC 인터프리터에 적응하면서, 수식은 다음 항목들로 구성된다:

- 정수
- 연산자 + - / * ^ = ( ) < > >= <= <>
- 변수

Small BASIC에서, ^는 거듭제곱을 나타낸다.
= 는 할당문과 비교를 위해 사용된다.
하지만, BASIC 수식과 비교해서, =는 관계식에서 사용되는 연산자이다.
(기본 BASIC에서, 할당은 연산자가 아니라 할당문이다.)
같지 않다는 <>로 나타난다.
이 항목들은 대수학 규칙에 따른 수식에서 결합될 수 있다.
여기 몇 가지 예시가 있다:

```
7-8
(100-5)*14/6
a+b-c
10^5
A<B
```

연산자의 우선순위는 여기에 보여진다:

| Highest | ( ) |
| ------- | --- |
| | unary + - |
| | ^ |
| | * / |
| | + - |
| **Lowest** | < > <= >= <> = |

동등 연산자의 우선순위는 왼쪽으로부터 오른쪽으로 평가한다.
Small BASIC은 다음 추정들을 만든다:

- 모든 변수들은 단일 문자이다; 이것은 26개 변수, A ~ Z 문자가 이용 가능하다는 것을 의미한다.
- 변수는 민감한 경우가 아니다; 'a'와 'A'는 같은 변수로 다룬다.
- 모든 숫자는 double 형이다.
- 문자열 변수는 지원되지 않고, ""문자열 상수가 화면에 메시지를 쓰는데 사용될 수 있다.

이 추정들은 파서에서 비롯된다.

#### Small BASIC Tokens

Small BASIC 파서의 핵심은 getToken() 메소드이다.
이 메소드는 2장에서 보여진 것의 확장 버전이다.
이 변화는 단지 숫자 수식이 아니라, 키워드와 문자열과 같이 Small BASIC언어의 다른 요소들을 토큰화를 할 수 있다.

Small BASIC에서, 각 키워드 토큰은 두 가지 형식을 가진다: 외부적, 내부적.
외부적인 형식은 당신이 프로그램을 작성할 때 사용하는 텍스트 형식이다.
예를 들면, "PRINT"는 PRINT키워드의 외부적 형식이다.
비록 인터프리터가 각 토큰이 외부적 문자열 형식으로 사용되는 각 방법으로 설계되는 것은 가능하지만, 이것은 효율적이지 않기 때문에 수행되지 않는다.
대신에, Small BASIC은 토큰의 내부적 형식 위에서 작동하고, 그것은 단순히 정수 값이다.
예를 들면, PRINT 명령은 1로 표현된다; INPUT 명령은 2로, 기타 등등.
내부적 표현의 이점은 더 많이 빠른 코드가 문자열 보다는 정수로 작성될 수 있다.
getToken()의 작업은 외부적 형식으로부터 내부적 형식으로 토큰을 변환하는 것이다.
Small BASIC의 getToken() 메소드는 여기 보여진다.
한 번에 한 문자씩 프로그램을 진행한다.

```java
// Obtain the next token.
private void getToken() throws InterpreterException
{
    ...
}
```

SBasic은 getToken()에 의해 광범위하게 사용되는 다음의 인스턴스 변수와 인터프리터 코드의 나머지를 정의한다.

```java
private char[] prog;    // refers to program array
...
```

그 프로그램은 prog에 의해 참조되는 문자 배열에 저장된다.
인터프리터가 연산중인 특별한 위치에서 progIdx에 저장된다.
토큰의 문자열 버전은 token에 저장된다.
토큰 타입은 tokType에 저장된다.
키워드를 표현하는 토큰의 내부적 표현은 kwToken에 저장된다.

Small BASIC 파서는 5개의 토큰 타입을 인지한다:
DELIMITER, VARIABLE, NUMBER, COMMAND, 그리고 QUOTESTR.
DELIMITER는 연산자와 괄호 둘 모두를 위해 사용된다.
VARIABLE은 변수를 만났을 때 사용된다.
NUMBER은 숫자를 위해 사용된다.
COMMAND 유형은 BASIC 키워드가 발견될 때 할당된다.
COMMAND 타입의 토큰은 인터프리터에 의해 취하는 액션을 요구한다.
QUOTESTR은 따옴표 문자열을 위해 사용된다.

getToken()을 면밀히 보자.
만약 프로그램의 끝이 도달된다면, token은 EOP로 할당되고 메소드를 반환한다.
반면에, 이어지는 공간은 isSpaceOrTab() 메소드의 도움으로 스킵되고, isSpaceOrTab()은 그것의 인수가 띄어쓰기나 탭이라면, true를 반환한다.
그것은 BASIC이 새 줄 문자를 종결자로 인지하기 때문에 이 결정을 위해 자바의 Character.isWhitespace() 메소드(어떤 빈공간 문자로 true를 반환한다) 사용이 가능한 것은 아니다.
그러므로, Small BASIC에서, 빈 공간은 단지 띄어쓰기와 탭으로 제한된다.
trailing space 가 프로그램을 끝내지 않는다고 가정하면, 공간이 스킵되고나면, prog[progIdx]는 숫자, 변수, 키워드, carriage-return/linefeed sequence, 연산자, 또는 ""문자열을 가리킨다.

만약 다음 문자가 carriage return이라면, kwToken은 EOL과 같은 집합이고, carriage return/line feed sequence는 token에 저장되고, DELIMITER는 tokType에 넣어진다.

반면에, getToken()은 관계 연산자를 검사하고, <=와 같은, 두 문자 연산자가 될지도 모른다.
getToken()은 두 문자 연산자를 내부적인 한 문자 표현으로 변환한다.
NE, GE, LE 값은 SBasic내의 final 값으로 정의된다.
다음, getToken()은 다른 연산자를 검사한다.
만약 연산자의 어떤 유형이 발견된다면, token 안에 문자열로 반환되고, DELIMITER의 타입은 tokType에 위치된다.

만약 다음 문자가 연산자가 아니면, getToken()은 그것이 문자인지 보기위해 검사한다.
만약 문자라면, 토큰은 A 또는 X와 같은 변수나, PRINT와 같은 키워드 둘 중 하나가 될 것이다.
lookUp()메소드는 그것이 키워드인지 보기위해 검사한다.
만약 키워드라면, lookUp()메소드는 키워드의 적절한 내부적 표현을 반환한다.
만약 키워드가 아니라면, 토큰은 변수로 추정된다.

반면에, 

### The Interpreter

SBasic의 인터프리터 부분은 실제로 프로그램을 실행하는 코드이다.
일반적으로, 각 문장(할당을 제외하고)이 키워드와 함께 시작하기 때문에 Small BASIC 프로그램을 번역하는 것은 꽤 쉽다.
그러므로, 그 인터프리터는 각 줄의 시작 부분에 키워드를 포함하므로써 작동하고, 키워드가 특정하는 것을 한다.
이 프로세스는 전체의 프로그램이 번역될 때 까지 반복한다.
이 구역의 나머지는 인터프리터의 각 부분을 세세하게 시험한다.

#### The InterpreterException Class

인터프리터의 시작 부분에서, 당신은 InterpreterException 클래스를 발견할 것이다.
이것은 에러가 발생한다면 인터프리터가 던질 예외의 타입이다.
SBasic을 사용하는 코드는 이 예외를 다룰 것이다.
예외들은 문법 에러, 입출력 에러, 숫자 식의 에러에 의해 야기될 수 있다.

#### The SBasic Constructor

생성자는 당신이 번역을 원하는 Small BASIC 파일의 이름을 넘겨질 수 있다.
그러면 그것은 임시 버퍼를 이 파일이 읽혀지는 것으로 생성한다.
이 버퍼의 크기는 PROG_SIZE로 특정화되고, 임의로 10,000으로 설정한다.
이것은 SBasic이 번역할 수 있는 가장 큰 프로그램의 크기이다.
당신은 원하는 크기로 변경할 수 있다.

다음, 그 설정자는 loadProgram()을 호출하고, 실제로 프로그램을 읽고, 그것의 크기, 문자 또는 실패인 -1를 반환한다.
그러면, 정확하게 프로그램의 크기인 새 배열은 생성되고, prog를 할당하는 참조이다.
마지막으로, 그 프로그램은 이 배열로 복사된다.
그러므로, prog에 의해 참조되는 배열의 크기는 정확하게 프로그램의 크기와 같을 것이다.

loadProgram() 메소드는 여기에 나타난다:

```
// Load a program.
private int loadProgram(char[] p, String fname)
throws InterpreterException
{
int size = 0;
try {
FileReader fr = new FileReader(fname);
BufferedReader br = new BufferedReader(fr);
size = br.read(p, 0, PROG_SIZE);
fr.close();
} catch(FileNotFoundException exc) {
handleErr(FILENOTFOUND);
} catch(IOException exc) {
handleErr(FILEIOERROR);
}
// If file ends with an EOF mark, back up.
if(p[size-1] == (char) 26) size--;
return size; // return size of program
}
```

이 메소드의 대부분은 쉽게 이해될 수 있으나, 이 줄에 각별한 주의를 가한다:

```
// If file ends with an EOF mark, back up.
if(p[size-1] == (char) 26)  size--;
```

내용이 나타나면서, 이 줄은 파일의 끝인 EOF 표시를 제거한다.
아시다시피, 몇몇 텍스트 편집기는 end-of-file 마커(일반적으로 값 26)를 추가한다.
loadProgram()이 존재한다면 마크를 제거하여 두 가지 경우를 모두 다룬다.

#### The Keywords

Small BASIC이 번역하는 BASIC의 하위집합은 이 키워드들에 의해 나타난다.

```
PRINT
INPUT
IF
THEN
FOR
NEXT
TO
GOTO
GOSUB
RETURN
END
```

이 키워드들의 내부적 표현은 줄의 끝을 위한 EOL을 더하고, 여기 보여지는 것처럼, SBasic에서 final값으로 선언된다.

```
// Internal representation of the Small BASIC keywords.
final int UNKNCOM = 0;
final int PRINT = 1;
final int INPUT = 2;
final int IF = 3;
final int THEN = 4;
final int FOR = 5;
final int NEXT = 6;
final int TO = 7;
final int GOTO = 8;
final int GOSUB = 9;
final int RETURN = 10;
final int END = 11;
final int EOL = 12;
```

UNKNCOM의 값은 알려지지 않은 키워드를 나타내는 lookUp() 메소드에 의해 사용된다.

키워드의 외부적 표현에서 내부적 표현으로의 변환을 촉진시키기 위해, 외부적, 내부적 형식 둘 다 Keyword 객체들로 구성된 kwTable이라 불리는 테이블에 잡힌다.
둘 다 여기에 보인다:

```
// This class links keywords with their keyword tokens.
class Keyword {
    String keyword; // string form
    int keywordTok; // internal representation
    
    Keyword(String str, int t) {
        keyword = str;
        keywordTok = t;
    }
}

/* Table of keywords with their internal representation.
All keywords must be entered lowercase. */
Keyword kwTable[] = {
    new Keyword("print", PRINT), // in this table.
    new Keyword("input", INPUT),
    new Keyword("if", IF),
    new Keyword("then", THEN),
    new Keyword("goto", GOTO),
    new Keyword("for", FOR),
    new Keyword("next", NEXT),
    new Keyword("to", TO),
    new Keyword("gosub", GOSUB),
    new Keyword("return", RETURN),
    new Keyword("end", END)
};
```

다음에 보여진, lookUp() 메소드는 토큰을 내부적 표현으로 변환하는 kwTable을 사용한다.
만약 매치가 발견되지 않는다면, UNKNCOM은 반환된다.

```
/* Look up a token's internal representation in the
token table. */
private int lookUp(String s)
{
    int i;

    // Convert to lowercase.
    s = s.toLowerCase();

    // See if token is in table.
    for(i=0; i < kwTable.length; i++)
        if(kwTable[i].keyword.equals(s))
            return kwTable[i].keywordTok;
    return UNKNCOM; // unknown keyword
}
```

#### The run() Method

SBasic 객체가 생성된 후에, 캡슐화한 프로그램은 run()을 호출에 의해 실행되고, 여기에 보여진다:

```
// Execute the program.
public void run() throws InterpreterException {
    // Initialize for new program run.
    vars = new double[26];
    fStack = new Stack();
    labelTable = new TreeMap();
    gStack = new Stack();
    progIdx = 0;
    
    scanLabels(); // find the labels in the program
    
    sbInterp(); // execute
}
```

run() 메소드는 변수의 값, FOR 반복을 위한 스택, 레이블을 위한 트리 맵, GOSUB를 위한 스택을 저장하는 배열을 할당하는 것에 의해 시작된다.
다음에, 현재 번역되고 있는 프로그램의 위치를 저장하는 progIdx는 0으로 설정된다.
이 필드들은 run()이 호출되는 각 시간이 설정되서, 같은 프로그램의 실행이 반복되게 한다.

다음에, 레이블을 찾는 프로그램을 스캔하는 scanLabels()가 호출된다.
한 가지가 발견될 때, 레이블과 그것의 위치는 labelTable 맵에 저장된다.
실행 전에 모든 레이블을 찾음으로써, 프로그램의 실행속도는 향상된다.

마지막으로, sbInterp()는 프로그램의 실행을 시작하면서 호출된다.

#### The sbInterp() Method

sbInterp() 메소드는 Small BASIC 프로그램의 실행을 시작하고 제어한다.
이 메소드는 여기에 보여진다:

```
// Entry point for the Small BASIC interpreter.
private void sbInterp() throws InterpreterException
{
    // This is the interpreter's main loop.
    do {
    getToken();
    // Check for assignment statement.
    if(tokType==VARIABLE) {
        putBack(); // return the var to the input stream
        assignment(); // handle assignment statement
    }
    else // is keyword
        switch(kwToken) {
        case PRINT:
            print();
            break;
        case GOTO:
            execGoto();
            break;
        case IF:
            execIf();
            break;
        case FOR:
            execFor();
            break;
        case NEXT:
            next();
            break;
        case INPUT:
            input();
            break;
        case GOSUB:
            gosub();
            break;
        case RETURN:
            greturn();
            break;
        case END:
            return;
        }
    } while (!token.equals(EOP));
}
```

모든 인터프리터는 프로그램으로부터 다음 토큰을 읽고 그것을 실행하는 적절한 액션을 선택하는 고수준 반복에 의해 작동된다.
Small BASIC 인터프리터는 예외가 없다.
이 메인 반복은 sbInterp()에 포함된다.
이와 같이 작동한다.
처음에, 한 토큰은 프로그램에 의해 읽혀진다.
문법 에러가 발견되지 않는다고 추정하면서, 만약 그 토큰이 변수라면, 할당이 발생된다.
반면에, 그 토큰은 (무시되는 것)줄 번호이거나 키워드가 되어야 한다.
만약 그것이 키워드라면, 적절한 case 문장은 키워드의 내부적 표현을 포함하는 kwToken의 값을 기반으로 선택된다.
각각의 키워드는 그 메소드 자체에서 처리되며, 이어지는 섹션에 의해 차례로 설명된다.

#### Assignment

전통적인 BASIC에서, 할당은 연산자가 아니라, 문장이고, Small BASIC에 의해 다뤄지는 방법이다.
BASIC 할당문의 일반적인 형식은
`var-name = expression` 이다.

할당문은 여기 보여지는 assignment() 메소드를 사용하면서 번역된다:

```
// Assign a variable a value.
private void assignment() throws InterpreterException
{
    int var;
    double value;
    char vname;

    // Get the variable name.
    getToken();
    vname = token.charAt(0);
    
    if(!Character.isLetter(vname)) {
        handleErr(NOTVAR);
    return;
    }

    // Convert to index into variable table.
    var = (int) Character.toUpperCase(vname) - 'A';
    
    // Get the equal sign.
    getToken();
    if(!token.equals("=")) {
        handleErr(EQUALEXPECTED);
        return;
    }
    
    // Get the value to assign.
    value = evaluate();
    
    // Assign the value.
    vars[var] = value;
}
```

assignment()가 하는 첫 번째는 프로그램으로부터 토큰을 읽는다.
이것은 할당된 값을 가지는 변수가 될 것이다.
만약 타당한 변수가 아니라면, 에러는 제출될 것이다.
다음에, 동등 표시는 읽혀진다.
그러면, evaluate()는 변수에 할당하는 값을 얻기 위해 호출된다.
마지막으로, 그 값은 변수에 할당된다.
그 메소드는 식 파서와 getToken() 메소드가 많은 "골치아픈" 일을 하기 때문에 놀랍게도 단순하고 깔끔하다.

#### The PRINT Statement

BASIC에서, PRINT 문은 실제로 꽤나 강력하고 유연하다.
PRINT 문의 모든 기능을 지원하는 메소드를 생성하는 것은 이 장의 범위를 벗어나지만, Small BASIC에 의해 정의된 방법은 가장 중요한 기능을 지원한다.
PRINT 문의 일반적인 형식은 `PRINT arg-list` 이고, arg-list는 




### Enhancing and Expanding the Interpreter

Small BASIC 에 문장을 추가하는 것은 꽤 쉽다.
오직 이 장에서 나타난 문장에 잡혀있는 일반적인 형식을 따라라.
다른 변수 타입을 추가한다면, 당신은 그 타입과 변수의 값을 저장하는 클래스를 생성하는 것을 필요로 할 것이고, 변수를 저장하기 위해 이 객체의 배열을 사용한다.

### Creating Your Own Computer Language

Small BASIC을 향상시키고 개선하는 것이 그것의 연산자와 언어 인터프리터가 작동하는 방법과 더 친숙하게 되는 좋은 방법이기 때문에, 당신은 BASIC 언어에 제한되지 않는다.
당신은 단지 단순화된 자바의 하위집합을 포함하는 어떤 컴퓨터 언어에 대한 인터프리터를 작성하기 위해 이 장에서 규정된 같은 기술을 사용할 수 있다.
당신은 심지어 당신의 프로그래밍 스타일과 개성을 반영한 당신의 언어를 발명할 수 있다.
사실, Small BASIC 에 의해 사용되는 인터프리터 뼈대는 당신이 실험하길 원하는 특별한 언어 특징의 어떤 타입을 위한 완벽한 "test bench" 이다.
예를 들면, REPEAT/UNTIL 반복을 인터프리터에 추가하기 위해, 당신은 이 단계들을 따를 필요가 있다:

1. REPEAT 와 UNTIL 을 키워드로 추가하고, 그들의 정수 값을 정의해라.
2. REPEAT 와 UNTIL 을 main loop switch 문에 추가해라.
3. REPEAT 와 UNTIL 문을 진행하는 repeat() 와 until() 메소드를 정의해라.
(시작점으로 execFor()과 next()를 사용해라.)

For those readers who enjoy a challenge, try creating a script language that automates various computing tasks, such as copying or erasing files, compiling a program, and so on.
도전을 즐기는 독자들을 위해, 파일 복사 또는 삭제, 프로그램 컴파일, 기타 등등 과 같은 다양한 컴퓨터 작업을 자동화하는 스크립트 언어를 생성해봐라.
Then create an interpreter for that language.
그 다음 언어 인터프리터를 생성해라.
Such a language could provide an alternative to using standard batch files. 
각 언어가 기존 배치파일을 사용하기 위한 대안을 제공할 수 있다.
In essence, you could adapt the interpreter to support your own proprietary batch processing scheme.
본질적으로, 당신은 당신의 일괄처리 계획을 지원하기 위해 인터프리터를 적용할 수 있다.