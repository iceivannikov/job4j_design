package ru.job4j.generic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleStoreTest {
    private Store<Role> store;

    @BeforeEach
    public void init() {
        store = new MemStore<>();
        store.add(new Role("1", RoleType.ADMIN));
    }

    @Test
    void whenAddAndFindThenRoleIsAdmin() {
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo(RoleType.ADMIN);
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleIsAdmin() {
        store.add(new Role("1", RoleType.USER));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo(RoleType.ADMIN);
    }

    @Test
    void whenReplaceThenRoleIsUser() {
        store.replace("1", new Role("1", RoleType.USER));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo(RoleType.USER);
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleType() {
        store.replace("10", new Role("10", RoleType.GUEST));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo(RoleType.ADMIN);
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleTypeIsAdmin() {
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo(RoleType.ADMIN);
    }

    @Test
    void whenReplaceOkThenTrue() {
        boolean result = store.replace("1", new Role("1", RoleType.USER));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        boolean result = store.replace("10", new Role("10", RoleType.GUEST));
        assertThat(result).isFalse();
    }
}