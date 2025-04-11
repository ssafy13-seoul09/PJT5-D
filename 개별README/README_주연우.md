## 역할

- KMP를 활용한 영상 검색 시스템 

### UserService

사용자가 입력한 키워드를 기반으로 영상을 검색하는 시스템을 구현하였습니다. 

입력받은 키워드를 KMP 알고리즘을 통해 DB 속 영상 제목과 패턴을 비교하고, 패턴이 존재하는 영상 제목의 영상 정보를 제공합니다.  

전체 영상 제목을 리스트에 넣고 패턴과 제목을 비교합니다. 영문의 경우 대/소문자 간 차이가 있기 때문에 사용자에게 입력받은 키워드와 전체 영상 제목을 모두 소문자로 변환 후 KMP를 통해 패턴을 찾았습니다.  

패턴을 가지고 있는 영상들은 리스트에 담아서 포워드로 전송합니다.   


```java
String keyword = req.getParameter("keyword");
List<Video> result = service.searchByTitle(keyword);

// KMP
public static boolean KMP(String text, String pattern) {
	    int[] lps = buildLPS(pattern);
	    int i = 0, j = 0;

	    while (i < text.length()) {
	        if (text.charAt(i) == pattern.charAt(j)) {
	            i++;
	            j++;
	            if (j == pattern.length()) return true;
	        } else {
	            if (j != 0) {
	                j = lps[j - 1];
	            } else {
	                i++;
	            }
	        }
	    }
	    return false;
	}

	public static int[] buildLPS(String pattern) {
	    int[] lps = new int[pattern.length()];
	    int len = 0;
	    int i = 1;

	    while (i < pattern.length()) {
	        if (pattern.charAt(i) == pattern.charAt(len)) {
	            len++;
	            lps[i++] = len;
	        } else {
	            if (len != 0) {
	                len = lps[len - 1];
	            } else {
	                lps[i++] = 0;
	            }
	        }
	    }
	    return lps;
	}
```

영상 제목과 입력받은 키워드는 소문자로 변화한 후 비교합니다.

```java
// DB의 영상 제목과 키워드의 패턴 비교 
public List<Video> searchByTitle(String keyword) {
    List<Video> videos = selectAll();
    List<Video> result = new ArrayList<>();
    
    for (Video video : videos) {
        if (SearchUtil.KMP(video.getTitle().toLowerCase(), keyword.toLowerCase())) {
            result.add(video);
        }
    }
    return result;
}
```


### JSP

입력받은 키워드와 일치하는 제목이 없는 경우 검색 결과가 없다는 메세지를 출력합니다.

포워드 받은 리스트를 반복문을 통해 JSP에서 출력합니다.  


```jsp
<jsp:include page="/common/header.jsp" />
<title>검색 결과</title>

<c:when test="${empty videos}">
    <p>검색 결과가 없습니다.</p>
</c:when>

<c:forEach var="video" items="${videos}">
    <tr>
        <td><a href="video?act=reviewPage&youtubeId=${video.youtubeId}">
            ${video.title}</a></td>
        <td>${video.fitPartName}</td>
        <td>${video.youtubeId}</td>
        <td>${video.viewCnt}</td>
    </tr>
</c:forEach>
```

## 느낀점
1. 기능 기획 과정
ssafit 서비스에 필요한 기능을 고민하는 과정을 경험할 수 있었다. 지난 pjt에서는 필수적인 기본 기능 구현에 중점을 가졌었다. 이번 pjt에서는 추가적으로 사용자의 원활한 페이지 활용을 위한 기능들을 고민한 점이 기억에 남는다. 먼저, 유저 추천 서비스는 사용자가 자신이 팔로우한 사람이 팔로우하고 있는 유저의 정보를 제공받는다. 이를 통해 커뮤니티성을 활성화 시킬 수 있을 것이라 기대된다. 다음으로 검색 기능은 사용자가 관심있는 키워드를 검색했을 때 이를 제목에 포함하고 있는 영상들을 제공한다. 이를 통해 사용자는 자신이 원하는 정보를 수월하게 얻을 수 있을 것이라 기대된다. 이렇게 사용자 중심의 사고를 하려 노력했고 팀원과 이야기하면서 기능을 구체화하였다.

2. 알고리즘 구현
KMP 알고리즘을 활용하여 패턴 매칭을 통해 기능을 구현했다. 처음엔 Controller에 메서드를 추가적으로 만들까 했는데, controller보다는 util에 따로 알고리즘 구현을 빼는 것이 효율적이라고 생각해서 util에 만들었다. MVC패턴의 각 영역이 하는 역할을 고려하면서 구현을 하는 것에 집중하였다.  

한글을 잘 인식할까에 대한 고민이 있었는데 의외로 실제 구현을 할 때 영문 대,소문자를 처리하는 것에서 예상치 못한 문제가 있었다. 대소문자를 같은 문자여도 다르게 인식을 했기 때문에 이에 대한 전처리가 필요했다. 이를 해결하기 위해 KMP 알고리즘을 타기 전에 키워드와 영상 제목 모두 소문자로 변환해주어 수월하게 비교할 수 있도록 하였다. 이런 과정은 사용자가 대,소문자를 구별하지 않아도 대문자로 써도 소문자의 제목 탐색이 가능하고 그 반대의 경우도 가능하다는 장점이 있다.

3. service, controller의 범위 구분
처음에 controller에 영상 제목 리스트를 받아오는 과정과 패턴 탐색하는 과정을 모두 담았었다. 팀원인 승민님이 해당 부분은 service에 더 적합한 과정이라고 설명해주어 수정할 수 있었다. 사소한 부분처럼 보이지만, 기능이 많고 복잡해졌을 경우 이런 영역 구분이 더욱 중요할 것이라고 생각하게 되었다. 아직 mvc 패턴의 각 영역의 역할에 대한 구체적인 이해가 더욱 필요할 것이라고 생각하게 된 부분이었다.



