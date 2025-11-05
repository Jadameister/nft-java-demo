package application;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import jp.ethereum.contracts.Contracts_NFT_sol_NFT;
import jp.ethereum.contracts.Contracts_Market_sol_NFTMarket;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
//https://stackoverflow.com/questions/70742806/cannot-generate-abi-and-bin-files-when-smartcontract-contains-the-openzeppelin
//https://stackoverflow.com/questions/70742806/cannot-generate-abi-and-bin-files-when-smartcontract-contains-the-openzeppelin
//https://dev.to/dabit3/building-scalable-full-stack-apps-on-ethereum-with-polygon-2cfb
//https://medium.com/@web3developer/introduction-to-web3j-4caee02d3e8e
/*
  485  solcjs --bin --include-path node_modules/ --base-path . cont
  486  solcjs --bin --include-path node_modules/ --base-path . NFT.sol
  487  solcjs --bin --include-path node_modules/ --base-path . contracts/NFT.sol
  488  solcjs --bin --include-path node_modules/ --base-path . contracts/MARKET.sol
  489  solcjs --bin --include-path node_modules/ --base-path . contracts/Market.sol
  490  solcjs --bin --include-path node_modules/ --base-path . contracts/Market.sol
  491  solcjs --abi  --include-path node_modules/ --base-path . contracts/NFT.sol
  492  solcjs --abi  --include-path node_modules/ --base-path . contracts/Market.sol
 */

public class Main {



//nftMarket deployed to: 0x9fE46736679d2D9a65F0992F2272dE9f3c7fa6e0
//nft deployed to: 0xCf7Ed3AccA5a467e9e704C703E8D87F634fB0Fc9
    private static final String YOUR_PROVIDER_HERE="http://127.0.0.1:8545/";
    private static final String DEFAULT_ADDRESS="http://127.0.0.1:8545/";
    static Web3j web3j = Web3j.build(new HttpService(YOUR_PROVIDER_HERE));

    //Web3j myEtherWallet = Web3j.build(new HttpService("https://api.myetherapi.com/eth"));
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        System.out.println(clientVersion);
       // Contracts_NFT_sol_NFT contracts_nft_sol_nft=new Contracts_NFT_sol_NFT("address",web3j)

    }




}
