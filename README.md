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

하지만 카테고리를 누를 때 마다 로딩 시간이 사용해 보니 느리게 느껴짐  

Loading 시간을 최대한 줄이기 위해서 각 카테고리마다 하나의 Fragment 배정

상단의 tabar -> TabLayout 사용
ViewPager로 넘어가는 애니메이션 적용 & Framgent 관리

ViewPager2 + TabLayout 구조  

카테고리는 고정값이 아님, 따라서 Fragment를 api response에 따라서 구성해야 함.



### 상세 페이지  

상세 페이지 구현 방법에는 2가지가 있음

1. Activity
2. Fragment


메인 페이지에서 viewPager를 쓰는 방법을 채택한 이상, 구조 상 Fragment가 가능하다.  

</br>

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
## ISSUE
