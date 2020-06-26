package Core.Client;

public enum ServerOperation {
    disconnect,
    addWniosek,
    getWniosek,
    getListaWnioskowByStatus,
    zmienStatusWnioskuZatwierdzone,
    zmienStatusWnioskuOdrzucone,
    wniosekZaglosuj,
    getUser,
    getWydarzenie,
    addWydarzenie,
    getListaWydarzenByStatus,
    getAllWydarzenia,
    zaglosujWydarzenie,
    getGlosyUstawaByWniosek,
    getGlosyWydarzenieByWydarzenie,
    getGlosowanieWniosek,
    getGlosowanieWydarzenie,
    getLiczbaWnioskow,
    getLiczbaWydarzen
}
