# 트러블 슈팅
어떡 프로젝트를 진행하며 생기는 문제점들에 대해 파악하고 해결을 기록.

1. Server issue
    CPU 1개, 메모리 1GB, 디스크 50GB로 세팅
    이후 도커와 쿠버네티스를 설치하고 젠킨스로 파이프라인을 만들어 관리하려 했지만 CPU와 MEM가 100%로 솟구침
    1) 디스크는 많이 남아 swap 으로 처리
    2) 그래도 문제가 있어서 젠킨스만 활용하기로 결정
    3) 젠킨스 설정 후 배포 시 같은 증상 및 기존 nohup -jar 실행이 내려감, 서버 스케일 업 결정
    서버를 확장하기 위한 방법에는 크게 Scale up과 Scale out이 있다.
    Scale up은 서버 자체의 사양을 증가시키는 것이고, Scale out은 비슷한 사양의 서버를 여러대 두어 확장하는 방법이다.
    [jenkins] 최소사양
    (최소) CPU : Single Core / Memory : 2GB / Disk : 10.0GB
    (권장) CPU : Dual Core / Memory : 4GB / Disk : 10.0GB
    [docker] 최소사양
    (최소) CPU : Single Core / Memory : 2GB / Disk : 3.0GB/Linux Kernel 3.10
    (권장) CPU : Dual Core / Memory : 4GB / Disk : 10.0GB/Linux Kernel 3.10
    [k8s]최소 서버스펙
    (최소) CPU : Single Core / Memory : 2GB / Disk : 3.0GB/Linux Kernel 3.10
    (권장) CPU : Dual Core / Memory : 4GB / Disk : 10.0GB/Linux Kernel 3.10
    4) NCloud 에서 권장사양으로는 7만원 최소사양으로 CPU 2, MEM 2, DISK 50으로 4.6정도 된다.
    혼자가 아니라 팀이기에 더욱 고민된다.
2. Server hacking
    I have backed up all your databases.
    To recover them you must pay 0.008 BTC (Bitcoin) to this address: 1LZF6tKhxW971v12SeGELwmNbWCCd4nQU4.
    Backup List: utteok. After your payment email me at dbrestore4583@onionmail.
    org with your server IP (118.67.132.171) and transaction ID and you will get a download link to your backup.
    Emails without transaction ID and server IP will be ignored.
    1LZF6tKhxW971v12SeGELwmNbWCCd4nQU4
    database 는 살아 있는데, 모든 데이터가 삭제되었다.
    작은 프로젝트라고 보안을 신경쓰지않은 내 탓이다.
    1) 허용된 IP(개발자)만 Spring FTP SSH 접근 가능
       /etc/ssh/sshd_config MaxAuthTries 6(6번이상실패시 차단)
        vi /etc/hosts.allow 해당 광역대는 허용
        vi /etc/hosts.deny 거부할 광역대
       yum install fail2ban jwhois
    2) 80 port 외에 접근 불허
    3) 데이터베이스 특정 IP만 접근 가능
3. deploy spring using jenkins
    1) nohup & kill -9 not working
        nohup 은 백그라운드에서 작업하는 것이고 kill -9은 기존 백그라운드 작업을 종료시키는 것이다.
        구글에서 찾아보니 젠킨스에서 빌드 후 조치로 Post build task 로 작업을 하기에 무턱대고 따라했더니 일부는 작동하고 일부는 작동하지 않았다.
        If the log text matches somewhere in the build log file, the script will execute.
        설명을 잘 읽어보자.. Log text 를 설정해야 script 가 실행된다. 정상적이지 않아 문제가 있었던 것으로 보인다.
        빌드 후 항상 실행 되야하기에 Build Steps 에서 Execute shell 로 Script 를 실행했다.
    2) Log file branch processing
        nohup.out 에 쌓이는 로그를 확인할 때, 몇일만 두어도 파일이 커서 일 단위로 나누어 파일을 정리하고 싶었다.
    3) permission denied
        이것으로 꾀 애먹었다.

4. deploy react using jenkins
    1) Typescript error



