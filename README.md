# Afreeca tv pre-assignment

**Description** : 아프리카tv 사전과제  
**Contributor** : `BulTTaeng(BulTTaeng)`  
**Language** : Kotlin   

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

</details>

---  

## 화면 구성  

### 메인 페이지  

화면 state 보존 방식에는 다음 2가지 방법이 있음

1. 2.4.0-aplha 버전 이후 라이브러리 사용
2. 이전 버전 사용해서 Fragment Manager로 transaction 조절


1번 :

    카테고리를 탭 할 때 마다 network 작업 이루어짐.

2번 : 

    잦은 network 작업을 막을 수 있음.  
    swipe view를 사용해 swipe하면 새로운 데이터를 가져오게 해야 함.
    


사용자 입장에서 새로운 탭을 탭 할 때 마다 업데이트가 이루어져야 앱이 잘 작동하는지 확인 할 수 있음.  

잦은 network 작업에 대한 부담은 paging을 적용하여 해결할 수 있음  


==> 1번 방법 채택

</br>

Loading 시간을 최대한 줄이기 위해서 각 카테고리마다 하나의 Fragment 배정

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



---  

## 디자인 패턴   

MVVM패턴으로 작성 

- View와 Presenter의 결합도를 낮출 수 있음
- 코드량을 줄일 수 있음
- 확장성이 좋음
- 테스트 용이




---

## 효율성   

Fragment의 onCreateView에서 pagingData를 불러오는 작업을 하면 상세 페이지 -> 메인 페이지 상황에서도 다시 api를 호출하게 됨.  

상세 페이지를 Activity로 만들면 해결되지만, 자주 만들어 질 수 있는 상세 페이지의 특성 상 효율성 측면에서 Fragment를 사용하는게 더 좋은 해결책  

따라서 viewModel에 data를 만들어서 null check로 값을 불러 오거나, swipe 했을 때만 값을 불러오게 수정


## ViewModel과 View의 관계   

항상 category 목록을 가지고 있어야 한다.
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
Di, abstract class, interface를 사용하면 OCP도 지킬 수 있을 것 같음.
- Glide로 profile Image를 불러 올 때만 이상하게 random하게 안되는 현상이 있음 -> 실패하면 handler로 다시 Glide 요청하는 방식으로 해결, 다른 library는 gif지원이 되지 않아 보류