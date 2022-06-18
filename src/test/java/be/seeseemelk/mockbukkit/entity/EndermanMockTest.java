package be.seeseemelk.mockbukkit.entity;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.block.data.BlockDataMock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.material.MaterialData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndermanMockTest
{

	private ServerMock serverMock;
	private EndermanMock endermanMock;

	@BeforeEach
	void setUp()
	{
		serverMock = MockBukkit.mock();
		endermanMock = new EndermanMock(serverMock, UUID.randomUUID());
	}

	@AfterEach
	void tearDown()
	{
		MockBukkit.unmock();
	}

	@Test
	void testMaterialDataNotSet()
	{
		assertThrows(IllegalStateException.class, () -> endermanMock.getCarriedMaterial());
	}

	@Test
	void testMaterialDataSet()
	{
		MaterialData materialData = new MaterialData(Material.DIAMOND);
		endermanMock.setCarriedMaterial(materialData);

		assertEquals(materialData, endermanMock.getCarriedMaterial());
	}

	@Test
	void testBlockDataNotSet()
	{
		assertThrows(IllegalStateException.class, () -> endermanMock.getCarriedBlock());
	}

	@Test
	void testBlockDataSet()
	{
		BlockData blockData = new BlockDataMock(Material.DIRT);
		endermanMock.setCarriedBlock(blockData);

		assertEquals(blockData, endermanMock.getCarriedBlock());
	}

	@Test
	void testIsScreamingDefault()
	{
		assertEquals(false, endermanMock.isScreaming());
	}

	@Test
	void testIsScreaming()
	{
		endermanMock.setScreaming(true);
		assertEquals(true, endermanMock.isScreaming());
	}

	@Test
	void testHasBeenStaredAtDefault()
	{
		assertEquals(false, endermanMock.hasBeenStaredAt());
	}

	@Test
	void testHasBeenStaredAt()
	{
		endermanMock.setHasBeenStaredAt(true);
		assertEquals(true, endermanMock.hasBeenStaredAt());
	}

	@Test
	void testSpawnEnderMan()
	{
		WorldMock world = new WorldMock(Material.DIRT, 3);
		Entity entity = world.spawnEntity(new Location(world, 0, 0, 0), EntityType.ENDERMAN);
		assertInstanceOf(EndermanMock.class, entity);
		assertTrue(entity.isValid());
	}

}
