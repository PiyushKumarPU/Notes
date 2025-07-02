
# ✅ Spring Data JPA

---

## 1️⃣ What is Spring Data JPA?

**Spring Data JPA** builds on JPA (Hibernate) to remove boilerplate.  
- Generates repository implementations at runtime.  
- Supports derived queries, custom `@Query`, paging, sorting, auditing.  
- You focus on your domain model, Spring does the persistence plumbing.

---

## 2️⃣ CrudRepository vs PagingAndSortingRepository vs JpaRepository

| Interface | Adds |
|----------------|---------------------|
| `CrudRepository` | Basic CRUD |
| `PagingAndSortingRepository` | + Paging, Sorting |
| `JpaRepository` | + JPA-specific batch ops, flush |

✅ Use `JpaRepository` for real-world apps.

---

## 3️⃣ How query method naming works

`findByStatusAndName` → Spring parses the name → generates JPQL.  
Supports `And`, `Or`, `Between`, `Like`, `OrderBy`.

```java
List<User> findByStatusAndAgeGreaterThan(String status, int age);
```

---

## 4️⃣ `@Query`: JPQL vs native

- JPQL: Uses entity names/fields → portable.
- Native: Uses real table/column names → DB-specific.

```java
@Query("SELECT u FROM User u WHERE u.status = ?1")

@Query(value = "SELECT * FROM users WHERE status = :status", nativeQuery = true)
```

---

## 5️⃣ EntityManager vs JpaRepository

- `JpaRepository` → easy CRUD, auto transactional.
- `EntityManager` → fine-grained control: native SQL, custom flush, detach, merge.
- Use `EntityManager` for batch ops, custom result mapping.

---

## 6️⃣ Lazy vs Eager Fetching

| FetchType | What it does |
|----------------|-------------------------------|
| `LAZY` | Loads associations only when accessed |
| `EAGER` | Loads immediately with parent |

✅ Lazy saves queries but can cause `N+1`.  
✅ Fix with `JOIN FETCH` or `@EntityGraph`.

---

## 7️⃣ JOIN FETCH vs EntityGraph

| | JOIN FETCH | EntityGraph |
|----------------|----------------|----------------|
| How | Inline JPQL | Declarative |
| Good for | One-off fix | Reusable, works with derived queries |
| Limits | Not great for paging | Works with paging |

```java
@Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.items")

@EntityGraph(attributePaths = {"items"})
List<Order> findByStatus(String status);
```

✅ Always use `DISTINCT` or `Set` to avoid duplicates.

---

## 8️⃣ Projections

| Type | Use case |
|----------------|----------------|
| Interface | Simple field slices |
| DTO | Constructor projection with logic |
| Entity | Full graph |

```java
interface UserView { String getName(); }
List<UserView> findByStatus(String status);

@Query("SELECT new com.example.UserDTO(u.name) FROM User u")
```

---

## 9️⃣ Specifications & Query By Example (QBE)

- **Specifications:** Dynamic, type-safe queries with `where`, `and`, `or`.
- **QBE:** Pass probe object → Spring generates query.

```java
Specification<User> spec = where(hasStatus("ACTIVE")).and(nameContains("john"));
userRepo.findAll(spec);

Example<User> example = Example.of(probe);
```

---

## 10️⃣ Transaction Management

- CRUD methods auto-wrapped in transactions.
- Use `@Transactional` at service layer for multiple ops.
- Internal calls skip proxy → no TX.
- Only unchecked exceptions roll back by default.

---

## 11️⃣ persist vs merge vs save

| Method | Purpose |
|----------------|----------------|
| `persist` | New only → managed in context |
| `merge` | Updates detached → returns new managed instance |
| `save` | Smart helper → does both |

✅ Common pitfall: `merge` returns a new object → always use the returned ref.

---

## 12️⃣ Auditing

Track created/updated timestamps.

```java
@CreatedDate LocalDateTime created;
@LastModifiedDate LocalDateTime updated;
```

Add `@EnableJpaAuditing`, `AuditingEntityListener`.  
Use `AuditorAware` for `@CreatedBy`, `@LastModifiedBy`.

---

## 13️⃣ Optimistic vs Pessimistic Locking

| | Optimistic | Pessimistic |
|----------------|----------------|----------------|
| Lock style | `@Version` | DB lock |
| Good for | Rare conflicts | Critical consistency |
| Behavior | Fails with `OptimisticLockException` | Blocks others |

```java
@Version Long version;

Order o = em.find(Order.class, id, LockModeType.PESSIMISTIC_WRITE);
```

---

## 14️⃣ Pagination & Sorting

Use `Pageable`, `Page`, `Slice`.

```java
Page<User> findByStatus(String status, Pageable pageable);
```

`Page` → total count + metadata.  
`Slice` → no count, just "has next".

---

## 15️⃣ Testing

✅ Use `@DataJpaTest` + embedded DB or test containers.  
✅ Test real `@Query` and edge cases (joins, paging).  
✅ Mock repos only in service unit tests, not for repo logic.

Example:
```java
@DataJpaTest
class UserRepoTest {
  @Autowired UserRepository repo;
}
```

---