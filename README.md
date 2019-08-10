# **Art_of_Java**

## **1. The Genius of Java**

큰 관점에서 역사는 프로그래밍의 역사에 의해 작은 규모로 미러링된다.
첫 사회는 간단한 시작으로부터 일어났듯이, 프로그래밍도 마찬가지다.
굉장한 문명이 일어나 번영하고 쇠퇴하듯이, 프로그래밍 언어도 마찬가지다.
그러나, 국가의 흥망성쇠를 통해 인류는 성장했다.
유사한 방식으로, 각각의 새로운 언어가 이전 언어를 대체하면서, 프로그래밍의 지속적인 개선이 진행되었다.
역사를 통해, 로마 제국의 멸망, 1066년에 영국의 침입, 또는 세계를 변화시킨 첫 핵 폭발, 과 같이, 중심이 되는 사건이 계속되었다.
비록 더 작은 규모일지라도, 공통점은 프로그래밍 언어와 일치하다.
예를 들면, FORTRAN의 발명은 컴퓨터가 프로그램되는 방법을 영원히 변화시켰다.
또 다른 중요한 사건은 자바의 탄생이였다.

자바는 프로그래밍의 인터넷 시대의 시작을 표시하는 이정표이다.
인터넷 연결이 있는 곳이라면 어디서든 실행할 수 있는 애플리케이션을 만들기 위해 특별히 설계된 자바의 "write once, run anywhere" 철학은 새로운 프로그래밍 패러다임을 정의했다.
Gosling이 처음으로 비교적 작은 종류의 문제의 해결책으로 봤던 것은 다음 세대의 프로그래머들을 위한 프로그래밍 지형을 정의하는 힘이 되었다.
자바는 근본적으로 우리가 프로그래밍에 대해 생각했던 방법을 바꾸어 컴퓨터 언어의 역사는 두 분류로 나눌 수 있다 : Before Java and After Java

Before Java 세계에서 프로그래머들은 stand-alone 머신을 실행하는 프로그램을 창조했다.
After Java 세계에서 프로그래머들은 높은 기여, 네트워크 환경을 위한 프로그램을 창조한다.
더 이상 프로그래머는 single computer에 관하여 생각하지 않는다.
대신에, 네트워크는 컴퓨터이고 오늘날 우리 프로그래머들은 서버, 클라이언트, 호스트에 관하여 생각한다.

비록 자바의 개발은 인터넷의 필요에 의해 움직였지만, 자바는 단순히 "인터넷 언어" 가 아니다.
오히려, 이것은 꽉 찬 특징, 일반적인 목적 프로그래밍 언어 현대에 설계된, 네트워크 세계
이것은 자바가 거의 모든 타입의 프로그래밍에 적절하다는 것을 의미한다.
때때로 네트워크의 능력에 의해 overshadowed되기도 하지만, 자바는 프로그래밍의 기술을 발전시킨 많은 혁신적인 기능을 통합했다.
이것의 혁신은 여전히 오늘날 computing을 통해 파급된다.
예를 들면, C#의 몇 가지 측면은 자바에 의해 처음 mainstreamed된 요소를 본떠 만들었다.

이 책을 통해 우리는 다양한 응용 분야에 적용함으로써 자바의 폭넓은 능력을 입증할 것이다.
일부 애플리케이션은 네트워킹 속성과는 무관하게 언어의 힘을 입증한다.
그들이 자바 문법과 설계 철학의 표현력을 보여주기 때문에 우리는 이것의 "pure code" 예시들을 호출한다.
다른 사람들은 자바 언어와 API 클래스를 사용하여 정교한 네트워크 프로그램이 개발될 수 있다는 용이성을 설명한다.
일괄적으로, 애플리케이션은 자바의 힘과 범위를 보여준다.

우리가 자바의 탐구를 시작하기 전에, 우리는 첫 장에서 그것을 주목할만한 프로그래밍 언어로 만드는 몇 가지 특징을 지적하기 위해 시간이 걸릴 것이다.
이들은 우리가 말하는 "genius of Java" 를 반영하는 특징이다.

### **Simple Types and Objects: The Right Balance**

객체 지향 컴퓨터 언어의 설계자가 직면하는 가장 큰 도전 중의 하나는 객체 대 단순형 딜레마를 다루는 방법이다.
여기 그 문제가 있다.
개념상으로 순수한 관점 포인트로부터, 모든 데이터 타입은 객체가 되어야 하고, 모든 타입은 보편적인 부모 객체로부터 내려와야 한다.
이것은 모든 데이터 타입이 상속 특성의 공통 set를 공유함으로서, 동일하게 동작하게 만든다.
그 문제는 int 또는 double과 같이, 간단한 타입을 객체로 만드는 것은 객체 메카니즘에 의해 발생하는 추가 overhead 때문에 성능 저하를 야기할 수 있다는 것이다.
간단한 타입이 루프와 조건문을 제어하는 데 종종 사용되기 때문에, 이 추가 overhead 는 광범위하고 부정적인 결과를 가질 것이다.
그 트릭은 "모든것은 객체이다"라는 욕구와 "performance counts"라는 현실 사이에서 올바른 균형을 찾는 것이다.

자바는 객체와 간단한 타입 문제를 우아한 방식으로 해결한다.
처음에, 그것은 8개의 간단한 타입을 정의한다: byte, short, int, long, char, float, double, and boolean.
이 타입들은 직접 2진수 값으로 번역된다.
그러므로, int 타입의 변수는 어떤 추가 overhead 없이 CPU에 의해 직접 연산될 수 있다.
자바에서 간단한 타입은 그들이 다른 언어에 있는 것과 마찬가지로 빠르고 효율적이다.
그러므로, int에 의해 제어되는 for 루프는 어떤 객체 관련 문제에도 영향을 받지 않고 최대 속도로 실행된다.

자바에서 간단한 타입 외에, 다른 모든 타입들은 보편적인 Object 슈퍼클래스를 상속받은 객체이다.
그러므로 다른 모든 타입은 상속 기능을 공유한다.
예를 들면, toString() 메소드가 Object에 의해 정의되기 때문에 모든 객체는 toString() 메소드를 가진다.

간단한 타입이 객체가 아니기 때문에, 자바는 객체와 비객체를 조금 다르게 다루는 것이 자유롭다.
이것은 실제 genius of Java의 설계가 드러나는 곳이다.
자바에서, 모든 객체는 간단한 타입의 경우와 마찬가지로, 직접적이 아니라, 참조를 통해 접근된다.
그러므로, 당신의 프로그램은 결코 객체에 직접 작용하지 않는다.
이 접근을 사용함으로써, 몇 가지 이점이 뒤따르는데, 가장 중요한 것은 garbage collection 이다.
모든 객체가 참조를 통해 접근되기 때문에 garbage collection은 효율적으로 구현될 수 있다: 객체에 대한 참조가 없을 때, 그것은 재활용 될 수 있다.
또 다른 이점은 Object 타입의 참조 객체가 시스템에서 어떤 객체를 참조할 수 있다는 것이다.

물론, 참조를 통해 모든 객체에 접근하는 것은 overhead를 추가된다.
그 이유는 참조가 본질적으로 주소(즉, 포인터)이기 때문이다.
그러므로, 모든 객체 접근은 주소를 통해 간접적으로 발생한다.
비록 현대 CPU가 간접적인 접근을 효율적으로 처리하지만, 간단한 타입의 경우와 같이 간접적인 접근은 데이터 자체에서 직접 작동하는 것만큼 빠르지 않다.

비록 간단한 타입이 꽤 효율적일지라도, 간단한 타입에 대응하는 객체가 필요한 경우도 있다.
예를 들면, 당신은 실행 중에 정수의 목록을 생성하고, 더 이상 필요없을 때 정수를 재활용할지도 모른다.
이러한 상황의 타입을 다루기 위해, 자바는 Integer와 Double과 같은 간단한 타입 wrapper를 정의한다.
이 wrapper는 간단한 타입이 필요할 때 객체 계층에 참가하는 것을 가능하게 한다.

자바의 객체 대 간단한 타입 문제의 해결책은 올바른 균형을 잡는다.
그것은 효율적인 프로그램이 쓰여지는 것을 허락하지만, 동시에 간단한 타입의 수행에 대해 부정적인 영향을 끼칠 걱정 없이 객체 모델이 구현되는 것을 허락한다.

### **Memory Management Through Garbage Collection**

메모리 관리 기술로서 쓰레기 수집은 긴 시간이 걸리지만, 자바에서 새 생명을 얻는다.
C++과 같은 언어에서, 프로그래머가 사용하지 않는 객체를 명시적으로 풀어줌으로써, 메모리는 수동적으로 관리되어야 한다.
이것은 더 이상 필요하지 않은 이후에 리소스를 풀어주는 것을 잊어버리거나 여전히 사용되는 리소스를 풀어주는 것을 잊어버리는 것은 흔하기 때문에 문제의 근원이다.
자바는 당신이 메모리를 관리함으로써 이 문제들을 막는다.
이것은 자바에서 모든 객체는 참조를 통해 접근되기 때문에 효율적인 방식이 될 수 있다.
그러므로, garbage collector가 참조가 없는 객체를 발견할 때, 그것은 객체가 사용되지 않고 재활용될 수 있다는 것을 안다.
자바가 직접적으로 객체를 직접적으로 연산될 수 있도록 허락했다면, 그렇게 garbage collection의 효율적인 수단은 불가능하게 될 것이다.

자바의 garbage collection의 사용은 일반적으로 자바의 철학을 반영한다.
자바 설계자


## **2. A Recursive-Descent Expression Parser

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
    이것은 white processing the expression 오류를 마주한다면 파서에 의해 던져 질 exception의 타입이다.
    
}