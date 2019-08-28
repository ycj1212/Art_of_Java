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

#### The Download Constructor

#### The download() Method

#### The run() Method

