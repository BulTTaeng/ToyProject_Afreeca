# Afreeca tv pre-assignment

**Description** : 아프리카tv api를 사용한 토이 프로젝트  
**Contributor** : `BulTTaeng(BulTTaeng)`  
**Language** : Kotlin   

---

## 시연 영상

![](https://github.com/BulTTaeng/afreeca/blob/master/testing.gif)

---  

## History

<details>
<summary>접기/펼치기</summary><br>  

`2023-01-04`  
- init
- Api 호출 부, data class 구현

`2023-01-05`  
- view 구조 설정
- dynamic하게 category 호출
- EventFlow 추가
- repeatOnLifeCycle 추가 
- 방송 데이터 가져오기
- 방송 데이터 view적용z
- paging 적용
- swipe view 추가

`2023-01-06`  
- 디테일 페이지 구현
- glide error 해결

`2023-01-07`  
- recycler view item view 수정
- paging data source 분할
- paging data source들 wrap
- Fragment 코드 중복 해결
- recycler item에 carview 적용

`2023-01-08`  
- Paging end page 조절
- Paging footer 추가
- 네트워크 연결 check 추가
- Unit test 추가

`2023-03-31`  
- 메모리 누수 방지를 위한 view binding release
- loading state 동기화

</details>

---  

## 화면 구성  

### 메인 페이지  

화면 state 보존 방식에는 다음 2가지 방법이 있음

1. 2.4.0-aplha 버전 이후 라이브러리 사용
2. 이전 버전 사용해서 Fragment Manager로 transaction 조절
3. 절충안


1번 :

    View가 create 될 때 마다 network 작업 이루어짐.

2번 : 

    잦은 network 작업을 막을 수 있음.  
    swipe view를 사용해 swipe하면 새로운 데이터를 가져오게 해야 함.
    

3번 : 

    최신 버전의 navigation을 사용하되, viewPager + Fragment를 사용하여 매번 network 작업이 이루어지지 않게 설정  
    Swipe view는 필요


==> 절충안 채택

</br>

Loading 시간을 줄이기 위해서 각 카테고리마다 하나의 Fragment 배정

맨 처음에만 data를 불러오게 함.  

상단의 tabar -> TabLayout 사용
ViewPager로 넘어가는 애니메이션 추가 & Framgent 관리

ViewPager2 + TabLayout 구조  

카테고리는 고정값이 아님, 따라서 Fragment를 api response에 따라서 구성해야 함.


새로운 데이터를 긁어 오기 위해, swipeview 추가


### 상세 페이지  

상세 페이지 구현 방법에는 2가지가 있음

1. Activity
2. Fragment


메인 페이지에서 2번의 방법을 채택한 이상, 구조 상 Fragment가 가능하다.  
따라서 화면 전환 & overhead를 고려 Fragment를 사용하기로 결정.  
또한 safe args 사용도 가능.  

==> Fragment 사용

---  

## 디자인 패턴   

MVVM패턴으로 작성 

- View와 Model간의 결합도를 낮출 수 있음
- 코드량을 줄일 수 있음
- 확장성이 좋음
- 테스트 용이

---

## 효율성   

Fragment의 onCreateView에서 pagingData를 불러오는 작업을 하면 상세 페이지 -> 메인 페이지 상황에서도 다시 api를 호출하게 됨.  

상세 페이지를 Activity로 만들면 해결되지만, 자주 만들어 질 수 있는 상세 페이지의 특성 상 효율성 측면에서 Fragment를 사용하는게 더 좋은 해결책  

따라서 viewModel에 data를 만들어서 null check로 값을 불러 오거나, swipe 했을 때만 값을 불러오게 수정  

Network Module은 Singleton으로 한번만 create  

repeatOnStarted => view를 내리면 IO 작업 중지  
Event caching => EventFlowSlot를 사용해서 consume되지 않은 flow 가지고 있음


## StateFlow?

Data Holder로 stateFlow를 사용하지 않음.
stateflow로 데이터를 hot stream으로 뿌려주게 되면, 새로운 데이터가 생기면 다시 뿌려줌
현재 프로젝트 구조인 viewPager2 + tablayout을 사용하면 새로운 Fragemnt가 만들어 질 때,
pool과 cache를 위해 다음 Fragement로 만들어짐
그렇게 되면 다음 페이지의 정보가 현재 페이지에 적용이 되는 문제가 발생
물론 pool이나 cache의 크기를 0으로 설정해 버릴 수도 있지만 그렇게 되면 RecyclerView adapter를
사용하는 Viewpager2의 이점이 사라짐
따라서 StateFlow를 사용하지 않고, flow를 사용해 변수에 paging 데이터를 저장함.


## Why Glide and Coil?

Recyclerview에서 image가 있을 경우 coil이 더 적은 메모리를 사용함
따라서 Recyclerview안의 imageloader로는 coil 사용.
하지만 gif일 수 도 있고 아닐 수도 있는 Detail Page의 profile image의 경우
glide를 사용하면 가끔 잘 load하지 못하는 이슈가 있고, 
Coil의 경우 transform 과 함께 사용하면 gif가 적용되지 않는 이슈가 있다.
그래서 그 부분에서는 glide에 예외 처리 & 기본 이미지 설정과 함께
glide를 사용하였다. 

## ViewModel과 View의 관계   

category 목록을 가지고 있어야 한다.
category 목록은 메인 페이지의 모든 fragment가 사용한다.
=> activity의 viewModel 공유 방식 선택

---

## 구조  

SingleActivity  
메인 페이지에 있는 Fragment는 매우 유사=> RecycleBaseFrament 작성 & 상속
여러개의 Paging data source  -> PagingBaseClass 상속  
activity viewModel로 카테고리 데이터 공유

---

## ISSUE
- First, Second, Third Fragment 코드 중복이 너무 많음.  
Di, abstract class, interface를 사용하면 OCP도 지킬 수 있을 것 같음. -> base Fragment 코드로 해결
- Glide로 profile Image를 불러 올 때만 이상하게 random하게 안되는 현상이 있음 -> 실패하면 handler로 다시 Glide 요청하는 방식으로 해결, 
~~다른 library는 gif지원이 되지 않아 보류~~
- Coil도 ImageLoader를 구현하면, gif지원이 됨. 하지만 transformation과 같이 사용하면 지원해 주지 않음.   
(#32 이슈, https://coil-kt.github.io/coil/gifs/)
