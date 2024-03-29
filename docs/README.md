## 📌 기능 명세서

### 📝 예상 방문 날짜 입력 받기

- [x] 이벤트 플래너 소개 메세지를 출력한다.
- [x] 예상 방문 날짜를 입력 받는 메세지를 출력한다.
- [x] 고객 예상 방문 날짜를 입력 받는다.
- [x] 입력을 검증한다.

### 📝 주문 메뉴와 개수 입력 받기

- [x] 고객의 주문 메뉴와 개수를 입력 받는 메세지를 출력한다.
- [x] 고객의 주문 메뉴와 개수를 입력 받는다.
- [x] 입력을 검증한다.

### 🔢 할인 전, 총 주문 금액 연산하기

- [x] 선택된 메뉴를 바탕으로, 할인 전, 총 주문 금액을 연산한다.

### ⭕ 총 주문 금액에 따른 이벤트 진행 여부 확인하기

- [x] 총주문 금액 `10,000원 이상`부터 이벤트가 적용된다.
- [x] 이벤트가 적용되지 않을 시, 혜택 없는 미리보기를 제공한다.

### 🎁 총 주문 금액에 따른 샴페인 증정 여부 확인하기

- [x] 증정 이벤트 기간인지 확인한다. `2023.12.1 ~ 2023.12.31`
- [x] 총주문 금액이 `12만 원 이상`인지 확인한다.
    - [x] `12만 원 이상`이면, 샴페인 `1개` 증정

### 🪄 할인 정책 적용하기

- 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격

**크리스마스 디데이 할인**

- [x] 크리스마스 디데이 할인 기간인지 확인한다. `2023.12.1 ~ 2023.12.25`
    - [x] 할인 금액 연산 : `1,000원`으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 `100원`씩 증가
    - [x] `총주문 금액`에서 할인한다.
        - [x] 연산한 할인 금액 만큼 할인
        - (e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)

**평일 할인**

- [x] 평일 할인 기간인지 확인한다. `2023.12.1 ~ 2023.12.31`, `일요일~목요일`
    - [x] `디저트 메뉴의 메뉴 1개당` 할인한다.
        - [x] `2,023원` 할인

**주말 할인**

- [x] 주말 할인 기간인지 확인한다. `2023.12.1 ~ 2023.12.31` `금요일, 토요일`
    - [x] `메인 메뉴의 메뉴 1개당` 할인한다.
        - [x] `2,023원` 할인

**특별 할인**

- [x] 특별 할인 기간인지 확인한다.`2023.12.1 ~ 2023.12.31`
- [x] 이벤트 달력에 별이 있는지 확인한다.
    - [x] `총주문 금액`에서 할인한다.
        - [x] `1,000원` 할인한다.

### 🔢 총혜택 금액 연산하기

- [x] 적용된 할인 정책을 바탕으로, 총 혜택 금액을 연산한다.
- [x] 할인 후 예상 결제 금액을 연산한다.
- 할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액

### 🎖️ 이벤트 배지 부여하기

- 총혜택 금액에 따라 이벤트 배지의 이름을 다르게 보여준다.
- 이 배지는 2024 새해 이벤트에서 활용된다.
- 배지에 따라 새해 이벤트 참여 시, 각각 다른 새해 선물을 증정할 예정이다.
- [x] `2만 원 이상`: 산타
- [x] `1만 원 이상`: 트리
- [x] `5천 원 이상`: 별

### 🧾 우테코 식당에서 받을 이벤트 혜택 미리 보기

- [x] 주문 메뉴와 개수를 출력한다.
    - [x] 출력 순서는 입력한 순이다.
- [x] 할인 전 총주문 금액을 출력한다.
- [x] 증정 메뉴를 출력한다.
    - [x] 증정 이벤트에 해당하지 않는 경우, 증정 메뉴 "없음"
- [x] 혜택 내역을 출력한다.
    - [x] 고객에게 적용된 이벤트 내역만 보여준다.
    - [x] 적용된 이벤트가 하나도 없다면 혜택 내역 "없음"
    - [x] 출력 순서는 `크리스마스 디데이` - `평일` - `주말` - `특별` 할인 순이다.
- [x] 할인 후 예상 결제 금액을 출력한다.
- [x] 12월 이벤트 배지를 출력한다.
    - [x] 이벤트 배지가 부여되지 않는 경우, "없음"

---

## 🚀 리펙토링 사항

- [x] inputView에서 값을 넘길 때 DTO로 넘기게 한다.
  - [x] 내부에서 검증, 파싱을 진행한다.
  - [x] visitDate의 경우 Integer로 반환하던 값을 int로 변환
    - Integer로 반환하면 null을 가질 수도 있다는 인상을 줌 + 성능 이슈

- [ ] discountCalculator의 책임을 분리한다. 
  - 기존 코드는 SRP, OCP 위반
  - [ ] 할인 정책별로 객체를 만든다. 
  - [ ] eventSchedular, discountPolicy의 책임을 분배한다.

- [x] 월, 일에 의존하는 여러 이벤트에 재사용 가능하도록 날짜 관리 객체 생성

- [ ] visiteDateSummary에서 `%d월 %d일` 만 넘기게 한다.
  - DTO가 view의 책임까지 가지고 있음.

- [ ] 객체가 스스로 할 수 있는 일을 외부에서 처리하고 있는지 확인한다.

- [ ] 생성자 주입을 적용한다.
  - 유연하고 확장 가능한 코드.

- [ ] setter 삭제, 생성자 주입. (PromotionSummary)

- [ ] Service단에서 상태값을 가지지 않게 한다. 
  - 싱글톤 고려!
  
- [ ] Christmas Promotion에 대한 Exception을 추상클래스를 만들어 상속받게 한다.

- [ ] static 함수만 있는 유틸 클래스들은 생성자를 private으로 잠군다.

- [ ] 하위 필드를 조회해야 하는 경우, 일반 클래스에 대해 정적 팩토리 메서드 패턴을 적용해본다.

- [ ] early-return을 적용한다.

- [x] ~~Optional을 함수의 반환값, 필드값으로 변환한다.~~ null을 반환하지 않고, NONE 필드 생성
  -  파라미터로 쓰는 것은 안티 패턴이다.

- [ ] Assert Softly를 활용한다.(DiscountCalculator)
  - 테스트 코드를 작성할 때 여러 Assert문이 있으면 위에 테스트가 실패했을 때 아래 테스트는 작동하지 않는다


---

## 🔮 입력 예외

<U>모든 에러 메시지는 "[ERROR]"로 시작한다.</U>

### 식당 방문 날짜

- 12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
- [x] 입력이 없는 경우
- [x] 정수가 아닌 경우

### 주문 메뉴와 개수

- 주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
- [x] 입력이 없는 경우
- [x] 메뉴 형식이 예시와 다른 경우
    - [x] 메뉴가 한글이 아닌 경우
    - [x] 입력이 양의 정수가 아닌 경우
    - "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."

---

## 📞 비즈니스 조건 예외

<U>모든 에러 메시지는 "[ERROR]"로 시작한다.</U>

### 식당 방문 날짜

- [x] 방문할 날짜는 1 이상 31 이하가 아닌 경우
    - "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."

### 주문 메뉴와 개수

- [x] 음료만 주문 시, 주문할 수 없다.
- [x] 중복 메뉴를 입력해서는 안된다.(e.g. 시저샐러드-1,시저샐러드-1)
    - 이 경우는 시저샐러드-2를 해야 유효한 형태다.
    - "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
- [x] 메뉴판에 없는 메뉴를 입력할 수 없다.
    - "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
- [x] 메뉴는 한 번에 최대 20개까지만 주문할 수 있다.
- [x] 메뉴의 개수는 1 이상의 숫자만 입력되어야 한다.
    - "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."

---

## ⚙️ 구조
```
└─christmas
    ├─config
    ├─constant
    ├─controller
    ├─exception
    │  ├─business
    │  └─input
    ├─model
    │  ├─policy
    │  │  ├─calendar
    │  │  └─discount
    │  ├─summary
    │  └─vo
    ├─service
    └─view
        ├─input
        │  ├─parser
        │  └─validator
        └─output

```


---

## 🔎 예시

기대하는 예시를 한 개만 들어서 설명했지만, 더 다양한 사례가 있을 것으로 예상됩니다.

```
안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
3
주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1
12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
 
<주문 메뉴>
티본스테이크 1개
바비큐립 1개
초코케이크 2개
제로콜라 1개
 
<할인 전 총주문 금액>
142,000원
 
<증정 메뉴>
샴페인 1개
 
<혜택 내역>
크리스마스 디데이 할인: -1,200원
평일 할인: -4,046원
특별 할인: -1,000원
증정 이벤트: -25,000원
 
<총혜택 금액>
-31,246원
 
<할인 후 예상 결제 금액>
135,754원
 
<12월 이벤트 배지>
산타
```

```
안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
26 
주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
타파스-1,제로콜라-1 
12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
 
<주문 메뉴>
타파스 1개
제로콜라 1개

<할인 전 총주문 금액>
8,500원
 
<증정 메뉴>
없음
 
<혜택 내역>
없음
 
<총혜택 금액>
0원
 
<할인 후 예상 결제 금액>
8,500원
 
<12월 이벤트 배지>
없음
```

