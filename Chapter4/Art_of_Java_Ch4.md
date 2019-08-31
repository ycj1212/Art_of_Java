# **Art_of_Java**

## **4. Creating a Download Manager in Java**

Have you ever had an Internet download interrupted before its completion, just to put you back at square one?

단지 원점으로 돌려놓기 위해서 인터넷 다운로드가 완료되기 전에 중단한 적이 있는가?

If you connect to the Internet with a dialup connection, it's very likely that you've run into this all too common nuisance.

만약 당신이 전화 접속 연결로 인터넷에 연결한다면, 당신은 모든 흔한 골칫거리를 마주쳤을 것이다.

Everything from call waiting disconnects to computer crashes can leave a download dead in its tracks.

단절을 기다리는 호출로부터 컴퓨터 충돌로의 모든 것들은 그들의 경로에서 다운로드 정지를 남길 수 있다.

To say the least, restarting a download from scratch over and over can be a very time-consuming and frustrating experience.

조금도 과장하지 않고, 스크래치가 여러 번 반복되는 것으로부터 다운로드를 재시작하는 것은 매우 시간 소모가 크고 답답한 경험이 될 수 있다.

A little-known fact is that many interrupted downloads can be resumed.

잘 알려지지 않은 사실은 많은 방해된 다운로드는 재개될 수 있다는 점이다.

This allows you to recommence downloading from the point at which a download terminates instead of having to begin anew.

이것은 당신이 새로 시작해야 하는 대신에 다운로드가 끝나는 지점으로부터 다운로드를 다시 시작하는 것을 허락한다.

However, most of today’s Internet browsers either don’t make this feature
readily available or make it overly cumbersome to use.

그러나, 오늘날의 인터넷 브라우저 대부분 이 특징을 손쉽게 이용가능하거나, 사용하기 매우 복잡하거나 둘 중 하나이다.

Enter the Download Manager: a tool that manages Internet downloads for you and makes simple work of resuming interrupted downloads.

인터넷 다운로드를

At the core of the Download Manager’s usefulness is its ability to take advantage of
downloading only specific portions of a file. In a classic download scenario, a whole file
is downloaded from beginning to end. If the transmission of the file is interrupted for any
reason, the progress made toward completing the downloading of the file is lost. On the
contrary, the Download Manager can pick up from where an interruption occurs and then
download only the file’s remaining fragment. Not all downloads are created equal, though,
and there are some that simply cannot be resurrected. Details on which files are and aren’t
resumable are explained in the following section.
Not only is the Download Manager a useful utility, it is an excellent illustration of the power
and succinctness of Java’s built-in APIs—especially as they apply to interfacing to the Internet.
The previous two chapters showed the fundamental elegance of the Java language; this and
the following three chapters demonstrate the ease with which Java programs can access the
Internet. Because the Internet was a driving force behind the creation of Java, it should come
as no surprise that Java’s networking capabilities are unsurpassed. For example, attempting
to create the Download Manager in another language, such as C++, would entail significantly
more trouble and effort. Of course, that is the art of Java!

### Understanding Internet Downloads

### An Overview of the Download Manager

다운로드 관리자는 자바의 스윙 라이브러리를 기반으로 간단한 효율적인 GUI 인터페이스를 사용한다.
다운로드 관리자 창은 Figure 4-1에 보여진다.
스윙의 사용은 산뜻하고, 현대적인 시각과 느낌의 인터페이스를 준다.

GUI는 현재 관리되고 있는 다운로드의 목록을 유지한다.
목록에서 각 다운로드는 URL, 파일 바이트 크기, 완료에 대한 백분율로 진행되며, 그리고 현재 상태를 보고한다.
다운로드는 다음의 다른 상태의 하나가 될 수 있다:
Downloading, Paused, Complete, Error, or Cancelled.
GUI는 또한 목록에 다운로드를 추가하고, 목록의 각 다운로드의 상태를 바꾸는 제어를 가지고 있다.
목록에서 다운로드가 선택될 때, 그것의 현재 상태에 의존하며, 그것은 대체로 목록으로부터 paused, resumed, cancelled, 또는 removed가 될 수 있다.

다운로드 관리자는 기능적인 구성요소의 자연스럽게 분리하기 위해 몇 가지 클래스로 구분된다.
이들은 각각 Download, DownloadsTableModel, ProgressRenderer, DownloadManager 클래스이다.
DownloadManager 클래스는 GUI 인터페이스를 책임지고, 현재 다운로드의 목록을 보여주기 위해 DownloadsTableModel 과 ProgressRenderer 클래스를 사용한다.
Download 클래스는 "관리되는" 다운로드를 나타내고 실제로 파일의 다운로딩 수행에 책임을 진다.
다음 섹션에서, 우리는 이 각각의 클래스들을 자세하게 보여줄 것이고, 그들의 내부 작업을 강조하고, 어떻게 그들이 서로 각각 관계가 있는지 설명할 것이다.

### The Download Class

Download 클래스는 다운로드 관리자의 일꾼이다.
그것의 주 목적은 파일을 다운로드하고 파일의 내용을 디스크에 저장하는 것이다.
새로 다운로드 할 때마다 다운로드 관리자가 추가되고, 새로운 Download 객체는 다운로드를 다루기 위해 예시화 된다.

다운로드 관리자는 한 번에 여러 파일을 다운로드하는 능력을 가지고 있다.
이것을 달성하기 위해, 동시 다운로드의 각각은 독립적으로 실행하여야 한다.
각 개별 다운로드가 GUI에서 반영될 수 있도록 자신의 state를 관리하는 것이 필수적이다.
이것은 Download 클래스와 함께 달성된다.

Download 전체 코드는 여기에 보여진다.
Observable 을 상속받고, Runnable 을 구현하는 것을 명심해라.
각 부분은 이어지는 구역에서 자세하게 설명된다.

#### The Download Variables

Download 는 클래스로서 사용되는 다양한 상수를 명시한 몇몇의 static final 변수를 선언하면서 시작한다.
다음에, 4개의 인스턴스 변수가 선언된다.
url 변수는 다운로드 될 파일을 위한 인터넷 URL을 저장한다;
size 변수는 바이트 단위로 다운로드 파일의 크기를 저장한다;
download 변수는 지금까지 다운로드 된 바이트의 수를 저장한다;
그리고 status 변수는 다운로드의 현재 상태를 나타낸다.

#### The Download Constructor

Download 의 생성자는 url 인스턴스 변수로 할당되는 URL 객체의 형식에서 다운로드하기 위해 URL로의 참조를 넘겨받는다.
그것은 남아있는 인스턴스 변수를 그들의 초기 상태로 설정하고, download() 메소드를 호출한다.
size 는 아직 크기가 없다는 것을 나타내는 -1로 설정된다.

#### The download() Method

download() 메소드는 호출되는 Download 인스턴스에 대한 참조를 전달하여, 새로운 Thread 객체를 생성한다.
앞에서 말한 바와 같이, 각 다운로드가 독립적으로 실행하는 것은 필수적이다.
Download 클래스가 독립적으로 실행되기 위해서, 자체의 스레드에서 실행하여야 한다.
자바는 스레드에 대한 탁월한 내장 지원을 가지고 있고, 스레드를 사용하면 쉽게 사용할 수 있다.
스레드를 사용하기 위해서, Download 클래스는 run() 메소드를 재정의함으로써 Runnable 인터페이스를 구현한다.
download() 메소드가 새로운 Thread 인스턴스를 인스턴스화 한 후에, 그것의 생성자 Runnable Download 클래스를 전달하면, 스레드의 start() 메소드를 호출한다.
start() 메소드를 호출하는 것은 Runnable 인스턴스의(Download 클래스의) run() 메소드가 실행되게 한다. 

#### The run() Method

run() 메소드를 실행할 때, 실제 다운로드가 진행된다.
그것의 크기와 중요성 때문에, 우리는 그것을 한 라인씩 면밀히 검사할 것이다.
run() 메소드는 이 줄들과 함께 시작한다:

RandomAccessFile file = null;
InputStream stream = null;
try {
    // Open connection to URL.HttpURLConnection connection = (HttpURLConnection) url.openConnection();

처음에, run()은 다운로드의 네트워크 스트림을 위한 변수를 제공한다.


### Enhancing the Download Manager

다운로드 관리자는 한번에 여러 파일들을 다운로드 하는 것에 더하여 다운로드를 멈추고 다시 시작하는 기능과 함께 매우 기능적이다;
그러나 당신이 스스로 시도하기를 원하는 몇가지 개선이 있다.
여기 몇 가지 생각이 있다:
프록시 서버 지원, FTP와 HTTPS 지원, 그리고 드래그앤드롭 지원.
특히 매력적인 개선 사항은 시스템 리소스가 풍부한 한밤중에 특정 시간에 다운로드를 예약할 수 있는 스케줄링 기능이다.

이 장에서 설명한 기술이 일반적인 경우에서 파일을 다운로드 것에 제한되지 않는다.
많은 다른 실용적인 코드의 사용이 있다.
예를 들면, 인터넷을 통해 배포되는 많은 소프트웨어 프로그램은 두 부분으로 나뉜다.
첫 번째 piece는 빠르게 다운로드 받을 수 있는 작고 아담한 응용 프로그램이다.
이 작은 응용 프로그램은 일반적으로 더 큰 두 번째 piece를 다운로드 하기 위한 작은 다운로드 관리자를 포함한다.
이 개념은 꽤 실용적이고, 특히 응용의 크기가 증가하면서, 전형적으로 다운로드 방해의 가능성에서 증가로 이끈다.
당신은 이 목적을 위해 다운로드 관리자를 적용한 것을 시도하는 것을 원한다.
