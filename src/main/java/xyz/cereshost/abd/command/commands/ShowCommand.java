package xyz.cereshost.abd.command.commands;

import xyz.cereshost.abd.Main;
import xyz.cereshost.abd.Service;
import xyz.cereshost.abd.Utils;
import xyz.cereshost.abd.command.Arguments;
import xyz.cereshost.abd.command.Command;
import xyz.cereshost.abd.model.*;

import java.util.ArrayList;
import java.util.List;

public class ShowCommand extends Command {
    public ShowCommand() {
        super("show", "muestras todos los elementos de una categoría");
    }

    @Override
    public void execute(Arguments arg) {
        switch (arg.get(0).toLowerCase()){
            case "catalogo" -> {
                if (arg.length() == 2){
                    CatalogData data = Main.CATALOG_DATA_BASE.getData(arg.get(1));
                    List<String> models = List.of(data.model(), "Modelos");
                    List<String> manufacturers = List.of(data.manufacturer(), "fabricante");
                    List<String> displacements = List.of(data.displacement(), "displacement");
                    List<String> prices = List.of(String.valueOf(data.price()), "Precios");

                    StringBuilder builder = new StringBuilder();
                    builder.append("\t").append(Utils.applySpace(1, "Modelos", models)).append("|")
                            .append(Utils.applySpace(1, "fabricante", manufacturers)).append("|")
                            .append(Utils.applySpace(1, "displacement", displacements)).append("|")
                            .append(Utils.applySpace(1, "Precios", prices)).append("|")
                            .append("\n");

                    builder.append("\t").append(Utils.applySpace(1, data.model(), models)).append("|")
                            .append(Utils.applySpace(1, data.manufacturer(), manufacturers)).append("|")
                            .append(Utils.applySpace(1, data.displacement(), displacements)).append("|")
                            .append(Utils.applySpace(1, String.valueOf(data.price()), prices)).append("|")
                            .append("\n");

                    Service.sendMessage(builder.toString());
                }else {
                    List<CatalogData> datas = Main.CATALOG_DATA_BASE.getAll();
                    List<String> models = new ArrayList<>(datas.stream().map(CatalogData::model).toList());
                    List<String> manufacturers = new ArrayList<>(datas.stream().map(CatalogData::manufacturer).toList());
                    List<String> displacements = new ArrayList<>(datas.stream().map(CatalogData::displacement).toList());
                    List<String> prices = new ArrayList<>(datas.stream().map(CatalogData::price).map(String::valueOf).toList());
                    models.addFirst("Modelos");
                    manufacturers.addFirst("fabricante");
                    displacements.addFirst("displacement");
                    prices.addFirst("Precios");

                    StringBuilder builder = new StringBuilder();
                    builder.append("\t").append(Utils.applySpace(1, "Modelos", models)).append("|")
                            .append(Utils.applySpace(1, "fabricante", manufacturers)).append("|")
                            .append(Utils.applySpace(1, "displacement", displacements)).append("|")
                            .append(Utils.applySpace(1, "Precios", prices)).append("|")
                            .append("\n");

                    for (CatalogData data : datas) {
                        builder.append("\t").append(Utils.applySpace(1, data.model(), models)).append("|")
                                .append(Utils.applySpace(1, data.manufacturer(), manufacturers)).append("|")
                                .append(Utils.applySpace(1, data.displacement(), displacements)).append("|")
                                .append(Utils.applySpace(1, String.valueOf(data.price()), prices)).append("|")
                                .append("\n");
                    }
                    Service.sendMessage(builder.toString());
                }
            }
            case "car" -> {
                if (arg.length() == 2){
                    CarData data = Main.INVENTORY_CAR_DATA_BASE.getData(arg.get(1));
                    List<String> tuitions = List.of(data.tuition(), "Matriculas");
                    List<String> models = List.of(data.model(), "Modelos");
                    List<String> paint = List.of(data.paint(), "Pinturas");
                    List<String> manufacturers = List.of(data.manufacturer(), "Fabricates");
                    List<String> displacements = List.of(data.displacement(), "Cilindrada");
                    List<String> prices = List.of(String.valueOf(data.price()), "Precios");

                    StringBuilder builder = new StringBuilder();
                    builder.append("\t").append(Utils.applySpace(1, "Matriculas", tuitions)).append("|")
                            .append(Utils.applySpace(1, "Modelos", models)).append("|")
                            .append(Utils.applySpace(1, "Pinturas", paint)).append("|")
                            .append(Utils.applySpace(1, "Fabricates", manufacturers)).append("|")
                            .append(Utils.applySpace(1, "Cilindrada", manufacturers)).append("|")
                            .append(Utils.applySpace(1, "Precios", prices)).append("|")
                            .append("\n");

                    builder.append("\t").append(Utils.applySpace(1, data.model(), models)).append("|")
                            .append(Utils.applySpace(1, data.manufacturer(), manufacturers)).append("|")
                            .append(Utils.applySpace(1, data.displacement(), displacements)).append("|")
                            .append(Utils.applySpace(1, String.valueOf(data.price()), prices)).append("|")
                            .append("\n");

                    Service.sendMessage(builder.toString());
                }else {
                    List<CarData> datas = Main.INVENTORY_CAR_DATA_BASE.getAll();
                    List<String> tuitions = new ArrayList<>(datas.stream().map(CarData::tuition).toList());
                    List<String> models = new ArrayList<>(datas.stream().map(CarData::model).toList());
                    List<String> paint = new ArrayList<>(datas.stream().map(CarData::paint).toList());
                    List<String> manufacturers = new ArrayList<>(datas.stream().map(CarData::manufacturer).toList());
                    List<String> displacements = new ArrayList<>(datas.stream().map(CarData::displacement).toList());
                    List<String> prices = new ArrayList<>(datas.stream().map(CarData::price).map(String::valueOf).toList());
                    tuitions.addFirst("Matriculas");
                    models.addFirst("Modelos");
                    paint.addFirst("Pinturas");
                    manufacturers.addFirst("fabricante");
                    displacements.addFirst("displacement");
                    prices.addFirst("Precios");

                    StringBuilder builder = new StringBuilder();
                    builder.append("\t").append(Utils.applySpace(1, "Matriculas", tuitions)).append("|")
                            .append(Utils.applySpace(1, "Modelos", models)).append("|")
                            .append(Utils.applySpace(1, "Pinturas", paint)).append("|")
                            .append(Utils.applySpace(1, "fabricante", manufacturers)).append("|")
                            .append(Utils.applySpace(1, "Displacement", displacements)).append("|")
                            .append(Utils.applySpace(1, "Precios", prices)).append("|")
                            .append("\n");

                    for (CarData data : datas) {
                        builder.append("\t").append(Utils.applySpace(1, data.tuition(), tuitions)).append("|")
                                .append(Utils.applySpace(1, data.model(), models)).append("|")
                                .append(Utils.applySpace(1, data.paint(), paint)).append("|")
                                .append(Utils.applySpace(1, data.manufacturer(), manufacturers)).append("|")
                                .append(Utils.applySpace(1, data.displacement(), displacements)).append("|")
                                .append(Utils.applySpace(1, String.valueOf(data.price()), prices)).append("|")
                                .append("\n");
                    }
                    Service.sendMessage(builder.toString());
                }
            }
            case "pieces" -> {
                if (arg.length() == 2){
                    PiecesData data = Main.INVENTORY_PIECES_DATA_BASE.getData(arg.get(1));
                    List<String> ids = List.of(String.valueOf(data.id()), "IDs");
                    List<String> dates = List.of(Utils.dateToString(data.date()), "Fechas");

                    StringBuilder builder = new StringBuilder();
                    builder.append("\t").append(Utils.applySpace(1, "DNI", ids)).append("|")
                            .append(Utils.applySpace(1, "Fachas", dates)).append("|")
                            .append("\n");

                    builder.append("\t").append(Utils.applySpace(1, String.valueOf(data.id()), ids)).append("|")
                            .append(Utils.applySpace(1, Utils.dateToString(data.date()), dates)).append("|")
                            .append("\n");

                    Service.sendMessage(builder.toString());
                }else {
                    List<PiecesData> datas = Main.INVENTORY_PIECES_DATA_BASE.getAll();
                    List<String> ids = datas.stream().map(PiecesData::id).map(String::valueOf).toList();
                    List<String> dates = datas.stream().map(PiecesData::date).map(Utils::dateToString).toList();
                    ids.addFirst("IDs");
                    dates.addFirst("Fechas");

                    StringBuilder builder = new StringBuilder();
                    builder.append("\t").append(Utils.applySpace(1, "IDs", ids)).append("|")
                            .append(Utils.applySpace(1, "Fechas", dates)).append("|")
                            .append("\n");

                    for (PiecesData data : datas) {
                        builder.append("\t").append(Utils.applySpace(1, Utils.dateToString(data.id()), ids)).append("|")
                                .append(Utils.applySpace(1, Utils.dateToString(data.date()), dates)).append("|")
                                .append("\n");
                    }
                    Service.sendMessage(builder.toString());
                }
            }
            case "cliente" -> {
                if (arg.length() == 2){
                    ClientData data = Main.CLIENT_DATA_BASE.getData(arg.get(1));
                    List<String> dnis = List.of(String.valueOf(data.getDni()), "DNI");
                    List<String> names = List.of(data.getName(), "Nombres");
                    List<String> address = List.of(data.getAddress(), "Dirección");
                    List<String> telephones = List.of(String.valueOf(data.getTelephone()), "Teléfonos");

                    StringBuilder builder = new StringBuilder();
                    builder.append("\t").append(Utils.applySpace(1, "DNI", dnis)).append("|")
                            .append(Utils.applySpace(1, "Nombres", names)).append("|")
                            .append(Utils.applySpace(1, "Dirección", address)).append("|")
                            .append(Utils.applySpace(1, "Teléfonos", telephones)).append("|")
                            .append("\n");

                    builder.append("\t").append(Utils.applySpace(1, String.valueOf(data.getDni()), dnis)).append("|")
                            .append(Utils.applySpace(1, data.getName(), names)).append("|")
                            .append(Utils.applySpace(1, data.getAddress(), address)).append("|")
                            .append(Utils.applySpace(1, String.valueOf(data.getTelephone()), telephones)).append("|")
                            .append("\n");

                    Service.sendMessage(builder.toString());
                }else {
                    List<ClientData> data = Main.CLIENT_DATA_BASE.getAll();
                    List<String> dnis = new ArrayList<>(data.stream().map(ClientData::getDni).map(String::valueOf).toList());
                    List<String> names = new ArrayList<>(data.stream().map(ClientData::getName).toList());
                    List<String> address = new ArrayList<>(data.stream().map(ClientData::getAddress).toList());
                    List<String> telephones = new ArrayList<>(data.stream().map(ClientData::getTelephone).map(String::valueOf).toList());
                    dnis.addFirst("DNI");
                    names.addFirst("Nombres");
                    address.addFirst("Dirección");
                    telephones.addFirst("Teléfonos");

                    StringBuilder builder = new StringBuilder();
                    builder.append("\t").append(Utils.applySpace(1, "DNI", dnis)).append("|")
                            .append(Utils.applySpace(1, "Nombres", names)).append("|")
                            .append(Utils.applySpace(1, "Dirección", address)).append("|")
                            .append(Utils.applySpace(1, "Teléfonos", telephones)).append("|")
                            .append("\n");

                    for (ClientData catalogData : data) {
                        builder.append("\t").append(Utils.applySpace(1, String.valueOf(catalogData.getDni()), dnis)).append("|")
                                .append(Utils.applySpace(1, catalogData.getName(), names)).append("|")
                                .append(Utils.applySpace(1, catalogData.getAddress(), address)).append("|")
                                .append(Utils.applySpace(1, String.valueOf(catalogData.getTelephone()), telephones)).append("|")
                                .append("\n");
                    }
                    Service.sendMessage(builder.toString());
                }
            }
            case "vendedor" -> {
                if (arg.length() == 2){
                    SellerData data = Main.SELLER_DATA_BASE.getData(arg.get(1));
                    List<String> dnis = List.of(String.valueOf(data.getDni()), "DNI");
                    List<String> names = List.of(data.getName(), "Nombres");
                    List<String> address = List.of(data.getAddress(), "Dirección");
                    List<String> telephones = List.of(String.valueOf(data.getTelephone()), "Teléfonos");

                    StringBuilder builder = new StringBuilder();
                    builder.append("\t").append(Utils.applySpace(1, "DNI", dnis)).append("|")
                            .append(Utils.applySpace(1, "Nombres", names)).append("|")
                            .append(Utils.applySpace(1, "Dirección", address)).append("|")
                            .append(Utils.applySpace(1, "Teléfonos", telephones)).append("|")
                            .append("\n");

                    builder.append("\t").append(Utils.applySpace(1, String.valueOf(data.getDni()), dnis)).append("|")
                            .append(Utils.applySpace(1, data.getName(), names)).append("|")
                            .append(Utils.applySpace(1, data.getAddress(), address)).append("|")
                            .append(Utils.applySpace(1, String.valueOf(data.getTelephone()), telephones)).append("|")
                            .append("\n");

                    Service.sendMessage(builder.toString());
                }else {
                    List<SellerData> data = Main.SELLER_DATA_BASE.getAll();
                    List<String> dnis = new ArrayList<>(data.stream().map(SellerData::getDni).map(String::valueOf).toList());
                    List<String> names = new ArrayList<>(data.stream().map(SellerData::getName).toList());
                    List<String> address = new ArrayList<>(data.stream().map(SellerData::getAddress).toList());
                    List<String> telephones = new ArrayList<>(data.stream().map(SellerData::getTelephone).map(String::valueOf).toList());
                    dnis.addFirst("DNI");
                    names.addFirst("Nombres");
                    address.addFirst("Dirección");
                    telephones.addFirst("Teléfonos");

                    StringBuilder builder = new StringBuilder();
                    builder.append("\t").append(Utils.applySpace(1, "DNI", dnis)).append("|")
                            .append(Utils.applySpace(1, "Nombres", names)).append("|")
                            .append(Utils.applySpace(1, "Dirección", address)).append("|")
                            .append(Utils.applySpace(1, "Teléfonos", telephones)).append("|")
                            .append("\n");

                    for (SellerData catalogData : data) {
                        builder.append("\t").append(Utils.applySpace(1, String.valueOf(catalogData.getDni()), dnis)).append("|")
                                .append(Utils.applySpace(1, catalogData.getName(), names)).append("|")
                                .append(Utils.applySpace(1, catalogData.getAddress(), address)).append("|")
                                .append(Utils.applySpace(1, String.valueOf(catalogData.getTelephone()), telephones)).append("|")
                                .append("\n");
                    }
                    Service.sendMessage(builder.toString());
                }
            }
            case "log" -> {
                if (arg.length() == 2){
                    LogSellData data = Main.SELL_LOG_DATA_BASE.getData(arg.get(1));
                    List<String> ids = List.of(String.valueOf(data.id()), "IDs");
                    List<String> models = List.of(data.model(), "Modelo");
                    List<String> clients = List.of(data.cliente(), "Clientes");
                    List<String> sellers = List.of(data.sellers(), "Vendedores");
                    List<String> tuitions = List.of(String.valueOf(data.tuition()), "Matriculas");
                    List<String> dates = List.of(Utils.dateToString(data.date()), "Fechas");
                    List<String> precios = List.of(String.valueOf(data.precio()), "Precios");

                    StringBuilder builder = new StringBuilder();
                    builder.append("\t").append(Utils.applySpace(1, "IDs", ids)).append("|")
                            .append(Utils.applySpace(1, "Modelo", models)).append("|")
                            .append(Utils.applySpace(1, "Clientes", clients)).append("|")
                            .append(Utils.applySpace(1, "Vendedores", sellers)).append("|")
                            .append(Utils.applySpace(1, "Matriculas", tuitions)).append("|")
                            .append(Utils.applySpace(1, "Fechas", dates)).append("|")
                            .append(Utils.applySpace(1, "Precios", precios)).append("|")
                            .append("\n");

                    builder.append("\t").append(Utils.applySpace(1, String.valueOf(data.id()), ids)).append("|")
                            .append(Utils.applySpace(1, data.model(), models)).append("|")
                            .append(Utils.applySpace(1, data.cliente(), clients)).append("|")
                            .append(Utils.applySpace(1, data.sellers(), sellers)).append("|")
                            .append(Utils.applySpace(1, data.tuition(), tuitions)).append("|")
                            .append(Utils.applySpace(1, Utils.dateToString(data.date()), dates)).append("|")
                            .append(Utils.applySpace(1, String.valueOf(data.precio()), precios)).append("|")
                            .append("\n");

                    Service.sendMessage(builder.toString());
                }else {
                    List<LogSellData> datas = Main.SELL_LOG_DATA_BASE.getAll();
                    List<String> ids = new ArrayList<>(datas.stream().map(LogSellData::id).toList());
                    List<String> models = new ArrayList<>(datas.stream().map(LogSellData::model).toList());
                    List<String> client = new ArrayList<>(datas.stream().map(LogSellData::cliente).toList());
                    List<String> sellers = new ArrayList<>(datas.stream().map(LogSellData::sellers).map(String::valueOf).toList());
                    List<String> tuitions = new ArrayList<>(datas.stream().map(LogSellData::tuition).toList());
                    List<String> dates = new ArrayList<>(datas.stream().map(LogSellData::date).map(String::valueOf).toList());
                    List<String> precies = new ArrayList<>(datas.stream().map(LogSellData::precio).map(String::valueOf).toList());

                    ids.addFirst("IDs");
                    models.addFirst("Modelo");
                    client.addFirst("Clientes");
                    sellers.addFirst("Vendedores");
                    tuitions.addFirst("Matriculas");
                    dates.addFirst("Fechas");
                    precies.addFirst("Precios");

                    StringBuilder builder = new StringBuilder();
                    builder.append("\t").append(Utils.applySpace(1, "IDs", ids)).append("|")
                            .append(Utils.applySpace(1, "Modelo", models)).append("|")
                            .append(Utils.applySpace(1, "Dirección", client)).append("|")
                            .append(Utils.applySpace(1, "Vendedores", sellers)).append("|")
                            .append(Utils.applySpace(1, "Matriculas", tuitions)).append("|")
                            .append(Utils.applySpace(1, "Fechas", dates)).append("|")
                            .append(Utils.applySpace(1, "Precios", precies)).append("|")
                            .append("\n");

                    for (LogSellData data : datas) {
                        builder.append("\t").append(Utils.applySpace(1, String.valueOf(data.id()), ids)).append("|")
                                .append(Utils.applySpace(1, data.model(), models)).append("|")
                                .append(Utils.applySpace(1, data.cliente(), client)).append("|")
                                .append(Utils.applySpace(1, data.sellers(), sellers)).append("|")
                                .append(Utils.applySpace(1, data.tuition(), tuitions)).append("|")
                                .append(Utils.applySpace(1, Utils.dateToString(data.date()), dates)).append("|")
                                .append(Utils.applySpace(1, String.valueOf(data.precio()), precies)).append("|")
                                .append("\n");
                    }
                    Service.sendMessage(builder.toString());
                }
            }
        }
    }
}
