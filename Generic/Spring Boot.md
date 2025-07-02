
# ✅ Spring Boot

---

## 1️⃣ Dependency Injection (DI)

**Q:** How does DI work? Why prefer constructor injection?

**A:** The IoC Container scans for beans, creates them, and resolves dependencies using constructor/field/setter injection. Constructor injection is best for immutability, explicit dependencies, testability, and null safety.

---

## 2️⃣ IoC Container

**Q:** What is the IoC Container?

**A:** Core of Spring. Manages beans: creates, wires, configures, and destroys them. Uses `ApplicationContext` to hold beans and metadata.

---

## 3️⃣ `@Component` vs `@Service` vs `@Repository` vs `@Controller`

**Q:** Differences?

**A:** All are stereotypes of `@Component`:
- `@Component`: generic bean.
- `@Service`: business logic, supports AOP.
- `@Repository`: data access layer, enables exception translation.
- `@Controller`: web MVC controller.

---

## 4️⃣ `@SpringBootApplication`

**Q:** What does it do?

**A:** Meta-annotation combining:
- `@Configuration`
- `@EnableAutoConfiguration`
- `@ComponentScan`

Marks the main config class, enables auto-config, scans components.

---

## 5️⃣ Auto-Configuration

**Q:** How does it work?

**A:** `@EnableAutoConfiguration` triggers auto-config. It scans `spring.factories` or `AutoConfiguration.imports`, applies `@Conditional` checks, configures beans based on classpath and context.

---

## 6️⃣ `application.properties` vs `application.yml`

**Q:** Difference?

**A:** Both hold config. `.properties` = flat key-value. `.yml` = supports nested structures, better for complex config. Same purpose.

---

## 7️⃣ Profiles & `@Profile`

**Q:** How do Profiles work?

**A:** Group beans/config for different environments (`dev`, `prod`). Activate with `spring.profiles.active` or `--spring.profiles.active`. Beans with `@Profile` only load if active.

---

## 8️⃣ `@RestController` vs `@Controller`

**Q:** Difference?

**A:** `@RestController` = `@Controller` + `@ResponseBody`. For REST APIs, returns JSON/XML directly. `@Controller` for MVC (returns views).

---

## 9️⃣ `@ControllerAdvice` & `@ExceptionHandler`

**Q:** Global error handling?

**A:** `@ControllerAdvice` applies cross-cutting exception handling to controllers. `@ExceptionHandler` defines how to handle specific exceptions.

---

## 10️⃣ Priority of Local vs Global Exception Handling

**Q:** What if both exist?

**A:** Method-level `@ExceptionHandler` in a controller takes priority. `@ControllerAdvice` only applies if no local handler matches.

---

## 11️⃣ Actuator

**Q:** What is Actuator?

**A:** Provides built-in endpoints for health, metrics, env, mappings, etc. Used for monitoring. Should be secured in production.

---

## 12️⃣ DevTools

**Q:** What is DevTools?

**A:** Adds dev-only features:
- Automatic restart
- LiveReload
- Dev-friendly props (disable caching)

Never for production — only for local dev.

---

## 13️⃣ `@SpringBootTest`

**Q:** What does it do?

**A:** Loads the **full** Spring context for integration tests. Expensive → use for full E2E tests only. Use slice tests (`@WebMvcTest`, `@DataJpaTest`) for focused tests.

---

## 14️⃣ `@MockBean` vs `@Mock`

**Q:** Difference?

**A:** `@MockBean` injects a Mockito mock into Spring’s context → replaces the real bean. `@Mock` is plain Mockito → does not integrate with Spring context.

---

## 15️⃣ `@Transactional` + Gotchas

**Q:** How does it work?

**A:** Uses AOP proxy → starts/commits/rolls back transactions around method calls.

- Internal self-calls bypass proxy → TX won’t run.
- Private methods can’t be proxied.

**Propagation types:**

- `REQUIRED`: Join or start new (default)
- `REQUIRES_NEW`: Suspend any current TX, always start new.
- `SUPPORTS`, `MANDATORY`, `NEVER`, `NESTED`, `NOT_SUPPORTED` for advanced scenarios.

**Other attributes:**

- `isolation`: DB isolation level.
- `rollbackFor`: Rollback for checked exceptions.
- `timeout`: Max TX duration.
- `readOnly`: Optimization for read-only queries.

✅ Use `REQUIRES_NEW` for **audit logs** to ensure logs persist even if the main TX rolls back.

---
