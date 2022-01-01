#ifndef CLOX_TABLE_H
#define CLOX_TABLE_H

#include "common.h"
#include "value.h"

typedef struct {
    ObjString* key;
    Value value;
} Entry;

typedef struct {
    int count;
    int capacity;
    Entry* entries;
} Table;

void initTable(Table* table);
void freeTable(Table* table);
bool tableHasKey(Table* table, ObjString* key);
bool tableGet(Table* table, ObjString* key, Value* outValue);
bool tableSet(Table* table, ObjString* key, Value value, bool makeFinal);
// only to be called when positive the table contains given key, i.e. after tableHasKey() has returned true
bool tableUpdate(Table* table, ObjString* key, Value value);
bool tableDelete(Table* table, ObjString* key);
void tableAddAll(Table* from, Table* to);
ObjString* tableFindString(Table* table, const char* chars, int length, uint32_t hash);

#endif
