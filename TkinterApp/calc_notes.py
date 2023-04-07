from tkinter import *
import re


notes_lines_list = []
string_list_val = []
relevant_string_list_val = []
notes_list = []
notes_subject_list = []
merged_list = []
color_list = []
bool_val = False
list_length = 0
result = 0

root=Tk()
root.geometry('800x600')

def retrieve_input():
    global bool_val
    global string_list_val
    inputValue=textBox.get("1.0","end-1c")
    if inputValue:
        print("IV ", inputValue)
        string_list_val = inputValue.splitlines()
        for index in range(0,len(string_list_val)):
            if 'Pflichtfächer:' in string_list_val[index]:
                bool_val = True
            if bool_val:
                relevant_string_list_val.append(string_list_val[index])
        
        search_notes_and_subjects(relevant_string_list_val)
        select_subject_names()
        merge_lists()
        get_length()
        get_average()
        select_color()
        print(list_length)
        display_result()
    reset()
    

def reset():
    global notes_lines_list
    global string_list_val
    global relevant_string_list_val
    global notes_subject_list
    global merged_list
    global bool_val
    global list_length
    global result
    notes_lines_list = []
    string_list_val = []
    relevant_string_list_val = []
    notes_list = []
    notes_subject_list = []
    merged_list = []
    color_list = []
    bool_val = False
    list_length = 0
    result = 0

def search_notes_and_subjects(relevant_string_list_val):
    global notes_subject_list
    global notes_list
    subject_index = 0
    for index in range(0,len(relevant_string_list_val)):
        if re.search('^(\d{2}\s\w{2,})(\s)*(\w)*(\s)*\d{5,}\t(.*\s{1})*\t$', relevant_string_list_val[index]):
            subject_index = index
            for index in range(index,len(relevant_string_list_val)):
                if 'SL ' in relevant_string_list_val[index]:
                    break
                elif 'PL ' in relevant_string_list_val[index]:
                    string_val = relevant_string_list_val[index+2]
                    if ' ' in string_val and ',' in string_val and len(string_val) == 4:
                        string_val_without_whitespaces = string_val.replace(" ", "")
                        string_val_without_comma = string_val_without_whitespaces.replace(',','.')
                        note_val = float(string_val_without_comma)
                        notes_list.append(note_val)
                        notes_subject_list.append(relevant_string_list_val[subject_index])
                        break                   

def select_subject_names():
    global notes_subject_list
    list_val = []
    for subject in notes_subject_list:
        line = re.sub("^(\d{2}\s\w{2,})(\s)*(\w)*(\s)*\d{5,}\t", "", subject)
        result_subject_name_val = re.sub("\t", "", line)
        result_subject_name = result_subject_name_val.rstrip()
        list_val.append(result_subject_name)
    notes_subject_list = list_val

def merge_lists():
    global merged_list
    global notes_list
    global notes_subject_list
    for index in range(0,len(notes_subject_list)):
        merged_list.append(notes_subject_list[index])
        try:
            merged_list.append(notes_list[index])
        except IndexError:
            break

def get_length():
    global list_length
    if len(notes_list) == len(notes_subject_list):
        list_length = len(notes_list)

def get_average():
    global notes_list
    global result
    sum = 0
    for note in notes_list:
        sum = sum + note
    result = sum / len(notes_list)

def select_color():
    global notes_list
    global color_list
    for note in notes_list:
        if note <= 2.3:
            color_list.append('green')
        elif note >= 2.7 and note <= 3.3:
            color_list.append('orange') 
        elif note >= 3.3 and note <= 4.0:
            color_list.append('red')

def display_result():
    list_x_vals = [410, 750]
    list_entry_width = [55, 5]
    y_val = 5
    index_merged_list = 0
    index_color_list = 0
    for i in range(0, list_length):
        for j in range(2):
            e = Entry(relief=GROOVE, width=list_entry_width[j], background=color_list[index_color_list])
            e.place(x=list_x_vals[j], y=y_val)
            e.insert(END, merged_list[index_merged_list])
            index_merged_list = index_merged_list + 1
        y_val = y_val + 20
        index_color_list = index_color_list + 1
    durchschnitt_label = Label(root, text="Durchschnitt: ")
    durchschnitt_label.place(x=1, y=450)
    durchschnitt_result_label = Label(root, text=round(result,2), )
    durchschnitt_result_label.place(x=80, y=450)


textBox=Text(root, height=25, width=50)
textBox.place(x=0, y=0) 
buttonCommit=Button(root, height=1, width=56, text="Calc", 
                    command=lambda: retrieve_input())
#command=lambda: retrieve_input() >>> just means do this when i press the button
buttonCommit.place(x=1, y=410)

mainloop()