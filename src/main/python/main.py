import os

import numpy as np


# Object of instance to store a lists of algorithms (String), a list of results (Double)
class Instance:
    def __init__(self, algorithms, results):
        self.algorithms = algorithms
        self.results = results

    def __str__(self):
        return str(self.algorithms) + " " + str(self.results)

# Function import all files in the folder "C:\Users\tomle\PycharmProjects\RechercheOperationnelle-M1-BDMA-RO"
def import_all_files():
    path = "../../../solutions"
    files = os.listdir(path)
    # import in list of result for all files parse with "," as separator
    result = []
    instances = {}
    for file in files:
        # parse the file name to get the algorithm name and the instance name
        file_name = file.split("-")
        # if the file name is not in the list of instance, we create a new instance
        if file_name[0] not in instances:
            instances[file_name[0]] = Instance([file_name[1]], [result[-1]])
        # else we add the algorithm and the result to the instance
        else:
            instances[file_name[0]].algorithms.append(file_name[1])
            instances[file_name[0]].results.append(result[-1])
    return result

if __name__ == '__main__':
    print(import_all_files())



